package com.djh.controller;

import com.djh.PageResult;
import com.djh.Result;
import com.djh.entity.Course;
import com.djh.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class CourseController {
    @Autowired
    private CourseService courseService;

    //根据页码查询课程
    @GetMapping("/courses")
    public Result getcourseByPage(@RequestParam(value = "name", required = false) String name,
                                  @RequestParam(value = "subject", required = false) Integer subject,
                                  @RequestParam(value = "target", required = false) Integer target,
                                  @RequestParam(value = "page", required = false, defaultValue = "1") Integer page,
                                  @RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize
    ) {
        PageResult<Course> courseByPage = courseService.getCourseByPage(name, subject, target, page, pageSize);

        return Result.success(courseByPage);
    }

    //增加课程
    @PostMapping("/courses")
    public Result InsertCourse(@RequestBody Course course) {
        courseService.insertCourse(course);
        return Result.success();
    }

    //删除课程
    @DeleteMapping("/courses/{id}")
    public Result deleteCourseById(@PathVariable Integer id) {
        courseService.deleteCourseById(id);
        return Result.success();
    }

    //根据id查询课程
    @GetMapping("/courses/{id}")
    public Result getCourseById(@PathVariable Integer id) {
        return Result.success(courseService.getCourseById(id));
    }



    //修改课程
    @PutMapping("/courses")
    public Result updateCourseById(@RequestBody Course course) {
        courseService.updateCourseById(course);
        return Result.success();
    }

    //查询所有课程
    @GetMapping("/courses/list")
    public Result getAllCourse() {
        return Result.success(courseService.getAllCourse());
    }

}
