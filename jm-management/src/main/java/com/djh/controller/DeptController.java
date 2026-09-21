package com.djh.controller;

import com.djh.Result;
import com.djh.entity.Dept;
import com.djh.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class DeptController {
    @Autowired
    DeptService deptService;

    //    添加部门
    @PostMapping("/depts")
    public Result addDept(@RequestBody Dept dept) {
        deptService.addDept(dept);
        return Result.success();
    }

    //   修改部门
    @PutMapping("/depts")
    public Result updateDept(@RequestBody Dept dept) {
        deptService.updateDept(dept);
        return Result.success();
    }

    //通过id查询部门
    @GetMapping("/depts/{id}")
    public Result getDeptById(@PathVariable Integer id) {
        return Result.success(deptService.getDeptById(id));
    }

    //    分页查找 部门
    @GetMapping("/depts")
    public Result getDeptByPage(@RequestParam(value = "name", required = false) String name,
                                @RequestParam(value = "status", required = false) Integer status,
                                @RequestParam(value = "page", required = false, defaultValue = "1") Integer page,
                                @RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize
    ) {
        return Result.success(deptService.getDeptByPage(name,status,page,pageSize));
    }
//    删除部门按照id
    @DeleteMapping("/depts/{id}")
    public Result deleteDept(@PathVariable Integer id) {
        deptService.deleteDept(id);
        return Result.success();
    }
//    查询所有 部门
    @GetMapping("/depts/list")
    public Result getDepts() {
        List<Dept> allDept = deptService.getAllDept();
        return Result.success(allDept);
    }

}
