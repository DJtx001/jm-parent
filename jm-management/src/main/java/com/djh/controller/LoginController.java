package com.djh.controller;

import com.djh.Result;
import com.djh.entity.User;
import com.djh.service.UserService;
import com.djh.vo.LoginResultVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class LoginController {
    @Autowired
    private UserService userService;
    @PostMapping("/login")
    public Result login(User user){
        log.error("用户登入接口报错",user);
        LoginResultVo userByUsername = userService.getUserByUsername(user);
        if(userByUsername == null){
            return Result.error("用户不存在");
        }else {
            return Result.success(userByUsername);
        }

    }
}
