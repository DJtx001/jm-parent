package com.djh.controller;

import com.djh.Result;
import com.djh.entity.User;
import com.djh.service.UserService;
import com.djh.vo.LoginResultVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class LoginController {
    @Autowired
    private UserService userService;

    /**
     * 用户登录 - /login
     */
    @PostMapping("/login")
    public Result login(@RequestBody User user) {
        log.info("用户登录接口, username: {}", user.getUsername());
        LoginResultVo userByUsername = userService.login(user.getUsername(), user.getPassword());
        return Result.success(userByUsername);
    }
}
