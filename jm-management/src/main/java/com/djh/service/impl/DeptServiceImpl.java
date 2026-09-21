package com.djh.service.impl;

import com.djh.PageResult;
import com.djh.entity.Dept;
import com.djh.mapper.DeptMapper;
import com.djh.service.DeptService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class DeptServiceImpl implements DeptService {
    @Autowired
    private DeptMapper deptMapper;
//添加部门信息
    @Override
    public void addDept(Dept dept) {
        LocalDateTime now = LocalDateTime.now();
        dept.setCreateTime(now);
        dept.setUpdateTime(now);
        deptMapper.addDept(dept);
    }
//修改部门信息
    @Override
    public void updateDept(Dept dept) {
        dept.setUpdateTime(LocalDateTime.now());
        deptMapper.updateDept(dept);
    }
//通过id查询 部门
    @Override
    public Dept getDeptById(Integer id) {
        return deptMapper.getDeptById(id);
    }
//根据分页查找
    @Override
    public PageResult<Dept> getDeptByPage(String name, Integer status, Integer page, Integer pageSize) {
        PageHelper.startPage(page, pageSize);
        PageInfo pageInfo = new PageInfo<>(deptMapper.getDeptByPage(name, status));
        PageResult pageResult = new PageResult(pageInfo.getTotal(),pageInfo.getList());
        return pageResult;
    }
//删除部门信息
    @Override
    public void deleteDept(Integer id) {
        deptMapper.deleteDept(id);
    }
//查询所有部门信息
    @Override
    public List<Dept> getAllDept() {
        List<Dept> allDept = deptMapper.getAllDept();
        return allDept;
    }


}
