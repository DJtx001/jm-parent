package com.djh.controller;

import com.djh.PageResult;
import com.djh.Result;
import com.djh.entity.Role;
import com.djh.service.RoleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/roles")
public class RoleController {
    @Autowired
    private RoleService roleService;
    //    分页查找
    @GetMapping
    public Result getRoleByPage(@RequestParam(required = false)String name,
                                @RequestParam(required = false)String label,
                                @RequestParam(required = false ,defaultValue = "1") Integer page,
                                @RequestParam (required = false ,defaultValue = "10")Integer pageSize){
        log.error("进入角色查询接口",name,label);
        PageResult<Role> roleByPage = roleService.getRoleByPage(name, label, page, pageSize);
        return Result.success(roleByPage);
    }
//    删除角色
    @DeleteMapping("/{id}")
    public Result deleteRoleById(@PathVariable Integer id){
        log.error("进入角色删除接口",id);
        roleService.deleteRoleById(id);
        return Result.success();
    }
//    根据id查询角色
    @GetMapping("/{id}")
    public Result getRoleById(@PathVariable Integer id){
        log.error("进入角色查询接口",id);
        Role roleById = roleService.getRoleById(id);
        return Result.success(roleById);
    }

//    添加角色
    @PostMapping
    public Result insertRole(@RequestBody Role role){
        log.error("进入角色添加接口",role);
        roleService.insertRole(role);
        return Result.success();
    }
//    修改角色
    @PutMapping
    public Result updateRole(@RequestBody Role role){
        log.error("进入角色修改接口",role);
        roleService.updateRole(role);
        return Result.success();
    }
//    查询所有角色
    @GetMapping("/list")
    public Result getAllRole(){
        List<Role> allRole = roleService.getAllRole();
        return Result.success(allRole);
    }
}
