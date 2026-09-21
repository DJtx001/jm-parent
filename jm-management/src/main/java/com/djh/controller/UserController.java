package com.djh.controller;

import com.djh.PageResult;
import com.djh.Result;
import com.djh.anno.LogOperation;
import com.djh.entity.User;
import com.djh.service.UserService;
import com.djh.dtp.UserDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/users")

public class UserController {
    @Autowired
    private UserService userService;
//    分页查找
    @GetMapping()
    public Result getUserByPge(UserDto userDto){
        log.error("进入用户查询接口",userDto);
        PageResult<User> userByPage = userService.getUserByPage(userDto);
        return Result.success(userByPage);
    }
//添加 用户
@LogOperation
    @PostMapping()
    public Result insertUser(@RequestBody User user){
        log.info("进入用户添加接口");
        userService.insertUser(user);
        return Result.success();
    }
//删除用户可以批量删除
@LogOperation
    @DeleteMapping ("/{ids}")
    public Result deleteUser(@PathVariable("ids") List<Integer> ids){
        log.error("删除用户接口报错",ids);
        userService.deleteUser(ids);
        return Result.success();
    }
//    修改用户
@LogOperation
    @PutMapping()
    public Result updateUser(@RequestBody User user){
        log.error("修改用户接口报错",user);
        userService.updateUser(user);
        return Result.success();
    }
}
