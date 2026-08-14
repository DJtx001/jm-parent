package com.djh.service;

import com.djh.PageResult;
import com.djh.entity.Course;

import java.util.List;

public interface CourseService {
//    增加方法
    void insertCourse(Course course);
//    删除方法
    void deleteCourseById(Integer id);
//    通过id查询方法
    Course getCourseById(Integer id);
//    通过条件查询课程
    List<Course> getCourseBySubject(String name,Integer subject,Integer target);

    PageResult<Course> getCourseByPage(String name, Integer subject, Integer target,Integer page, Integer pageSize);
}
