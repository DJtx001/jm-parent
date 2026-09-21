package com.djh.service.impl;

import com.djh.PageResult;
import com.djh.entity.Role;
import com.djh.mapper.RoleMapper;
import com.djh.service.RoleService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleMapper roleMapper;
    //    分页查找
    @Override
    public PageResult<Role> getRoleByPage(String name, String label,Integer page,Integer pageSize) {
        PageHelper.startPage(page, pageSize);
        PageInfo pageInfo = new PageInfo(roleMapper.getRoleByPage(name, label));
        PageResult pageResult = new PageResult(pageInfo.getTotal(),pageInfo.getList());
        return pageResult;
    }
    //    删除角色
    @Override
    public void deleteRoleById(Integer id) {
        roleMapper.deleteRoleById(id);
    }
    //    根据id查询角色
    @Override
    public Role getRoleById(Integer id) {
        Role roleById = roleMapper.getRoleById(id);
        return roleById;
    }
    //    添加角色
    @Override
    public void insertRole(Role role) {
        role.setUpdateTime(LocalDateTime.now());
        role.setCreateTime(LocalDateTime.now());
        roleMapper.insertRole(role);
    }
    //    修改角色
    @Override
    public void updateRole(Role role) {
        role.setUpdateTime(LocalDateTime.now());
        roleMapper.updateRole(role);
    }
    //    查询所有角色
    @Override
    public List<Role> getAllRole() {
        List<Role> allRole = roleMapper.getAllRole();
        return allRole;
    }

}
