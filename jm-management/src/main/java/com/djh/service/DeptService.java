package com.djh.service;

import com.djh.PageResult;
import com.djh.entity.Dept;
import com.github.pagehelper.Page;
import org.springframework.stereotype.Service;

public interface DeptService {
//    增加 部门
    void addDept(Dept dept);
//    修改部门
    void updateDept(Dept dept);
//    通过部门id查询部门
    Dept getDeptById(Integer id);
//    分页查找
    PageResult<Dept> getDeptByPage(String name, Integer status, Integer page, Integer pageSize);
}

