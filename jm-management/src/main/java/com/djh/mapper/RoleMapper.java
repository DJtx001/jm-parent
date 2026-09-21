package com.djh.mapper;

import com.djh.entity.Role;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface RoleMapper {
//    分页查找
    List<Role> getRoleByPage(String name,String label);
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
