package com.djh.service;

import com.djh.PageResult;
import com.djh.entity.User;
import com.djh.dtp.UserDto;
import com.djh.vo.LoginResultVo;

import java.util.List;

public interface UserService {
//    分页查找
    PageResult<User> getUserByPage(UserDto userDto);
//    添加 用户
    void insertUser(User user);
//    删除 用户可以批量删除
    void deleteUser(List<Integer> ids);
//    修改 用户
    void updateUser(User user);
//    根据用户名查询用户
    LoginResultVo getUserByUsername(User user);
//    登入逻辑
    LoginResultVo login(String username, String password);
}

