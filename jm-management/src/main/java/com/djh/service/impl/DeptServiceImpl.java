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
//    @Override
//    public PageResult<Dept> getDepts(String name, Integer status, Integer page, Integer pageSize) {
//        Integer deptCount = deptMapper.getDeptCount(name, status);
//        PageResult<Dept> pageResult = new PageResult<>(deptCount, deptMapper.getDepts(name, status, page, pageSize));
//        return pageResult;
//    }
//
//    @Override
//    public PageResult<Dept> getDeptsByPage(String  name,Integer status,Integer page,Integer size) {
//        PageHelper.startPage(page, size);
//        PageInfo<Dept> pageInfo = new PageInfo<>(deptMapper.getDeptByPageHepler(name, status));
//        PageResult<Dept> pageResult = new PageResult<>(pageInfo.getTotal(), pageInfo.getList());
//        return pageResult;
//    }
//    修改部门信息
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


}
