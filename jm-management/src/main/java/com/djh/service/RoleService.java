package com.djh.service;

import com.djh.PageResult;
import com.djh.entity.Role;

import java.util.List;

public interface RoleService {
    //    分页查找
    PageResult<Role> getRoleByPage(String name, String label,Integer page, Integer pageSize);
//    删除角色
    void deleteRoleById(Integer id);
//    根据id查询角色
    Role getRoleById(Integer id);
//    添加角色
    void insertRole(Role role);
//    修改角色
    void updateRole(Role role);
//    查询所有角色
    List<Role> getAllRole();
}
