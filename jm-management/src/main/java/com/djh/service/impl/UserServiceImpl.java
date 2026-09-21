package com.djh.service.impl;

import com.djh.PageResult;
import com.djh.entity.User;
import com.djh.exception.BusinessException;
import com.djh.mapper.UserMapper;
import com.djh.service.UserService;
import com.djh.vo.LoginResultVo;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.djh.dtp.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;
import utils.JwtUtils;

import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


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
        LocalDateTime now = LocalDateTime.now();
        user.setCreateTime(now);
        user.setUpdateTime(now);
        //Md5加密存储用户密码
        user.setPassword(DigestUtils.md5DigestAsHex((user.getPassword() + "djh").getBytes(StandardCharsets.UTF_8)));
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
        if (user.getPassword() != null && ! user.getPassword().equals("") ) {
//            密码不为空，则进行加密
            user.setPassword(DigestUtils.md5DigestAsHex((user.getPassword() + "djh").getBytes(StandardCharsets.UTF_8)));

        }
        userMapper.updateUser(user);

    }

    @Override
    public LoginResultVo login(String username, String password) {
        // 查询用户信息
        User user = userMapper.selectByUsername(username);
        // 密码采用与新增用户一致的MD5加盐加密方式
        if (user == null || !user.getPassword().equals(DigestUtils.md5DigestAsHex((password + "djh").getBytes(StandardCharsets.UTF_8)))) {
            throw new BusinessException("用户名或密码错误");
        }

        // 校验用户状态
        if (user.getStatus()==0) { // 0 表示停用状态
            throw new BusinessException("对不起, 您的账号已停用");
        }

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
}
