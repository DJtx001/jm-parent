package com.djh.service.impl;

import com.djh.PageResult;
import com.djh.entity.User;
import com.djh.exception.BusinessException;
import com.djh.mapper.UserMapper;
import com.djh.service.UserService;
import com.djh.utils.PasswordUtils;
import com.djh.vo.LoginResultVo;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.djh.dtp.UserDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import utils.JwtUtils;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Slf4j
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    //分页查找
    @Override
    public PageResult<User> getUserByPage(UserDto userDto) {
        PageHelper.startPage(userDto.getPage(), userDto.getPageSize());
        List<User> userByPage = userMapper.getUserByPage(userDto);
        PageInfo<User> userPageInfo = new PageInfo<>(userByPage);
        PageResult<User> userPageResult = new PageResult<>(userPageInfo.getTotal(), userPageInfo.getList());
        return userPageResult;
    }

    //增加用户并且加密密码
    @Override
    public void insertUser(User user) {
        if (user.getPassword() == null || user.getPassword().isEmpty()) {
            throw new BusinessException("密码不能为空");
        }
        LocalDateTime now = LocalDateTime.now();
        user.setCreateTime(now);
        user.setUpdateTime(now);
        // 密码使用 BCrypt 加密存储（自带随机盐，不同用户相同密码密文也不同）
        user.setPassword(PasswordUtils.encode(user.getPassword()));
        userMapper.insertUser(user);
    }

    //删除用户
    @Override
    public void deleteUser(List<Integer> ids) {
        userMapper.deleteUser(ids);
    }

    //修改用户
    @Override
    public void updateUser(User user) {
        user.setUpdateTime(LocalDateTime.now());
        if (user.getPassword() != null && !user.getPassword().isEmpty()) {
            // 密码不为空，重新加密
            user.setPassword(PasswordUtils.encode(user.getPassword()));
        } else {
            // 密码为空则不更新密码字段，避免把已有密码覆盖成空
            user.setPassword(null);
        }
        userMapper.updateUser(user);
    }

    @Override
    public LoginResultVo login(String username, String password) {
        // 查询用户信息
        User user = userMapper.selectByUsername(username);
        // 密码校验：BCrypt 与历史 MD5 双兼容，保证老账号也能登录
        if (user == null || !PasswordUtils.matches(password, user.getPassword())) {
            throw new BusinessException("用户名或密码错误");
        }

        // 校验用户状态
        if (user.getStatus() == 0) { // 0 表示停用状态
            throw new BusinessException("对不起, 您的账号已停用");
        }

        // 老密码自动升级：登录成功后把历史 MD5 密文替换为 BCrypt，实现平滑迁移
        upgradeLegacyPassword(user, password);

        // 构造登录结果
        LoginResultVo loginResult = new LoginResultVo();
        loginResult.setId(user.getId());
        loginResult.setUsername(user.getUsername());
        loginResult.setName(user.getName());
        loginResult.setImage(user.getImage());
        loginResult.setRoleLabel(user.getRoleLabel());

        Map<String, Object> claims = new HashMap<>();
        claims.put("id", user.getId());
        claims.put("username", user.getUsername());
        String jwt = JwtUtils.generateToken(claims);

        loginResult.setToken(jwt);

        return loginResult;
    }

    /**
     * 历史 MD5 密码平滑升级为 BCrypt
     * <p>只在老密码校验通过后执行，升级失败不影响本次登录
     */
    private void upgradeLegacyPassword(User user, String rawPassword) {
        if (PasswordUtils.isBcrypt(user.getPassword())) {
            return;
        }
        try {
            User upgradeUser = new User();
            upgradeUser.setId(user.getId());
            upgradeUser.setPassword(PasswordUtils.encode(rawPassword));
            upgradeUser.setUpdateTime(LocalDateTime.now());
            userMapper.updateUser(upgradeUser);
            log.info("用户[{}]的历史 MD5 密码已自动升级为 BCrypt", user.getUsername());
        } catch (Exception e) {
            log.warn("用户[{}]密码自动升级失败，不影响本次登录: {}", user.getUsername(), e.getMessage());
        }
    }
}
