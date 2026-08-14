package com.djh.service.impl;

import com.djh.PageResult;
import com.djh.entity.Course;
import com.djh.mapper.CourseMapper;
import com.djh.service.CourseService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class CourseServiceImpl implements CourseService {
    @Autowired
    private CourseMapper courseMapper;

    //增加课程
    @Override
    public void insertCourse(Course course) {
        LocalDateTime now = LocalDateTime.now();
        course.setUpdateTime(now);
        course.setCreateTime(now);
        courseMapper.insertCourse(course);
    }

    //通过id删除课程
    @Override
    public void deleteCourseById(Integer id) {
        courseMapper.deleteCourseById(id);
    }


    //根据id查询方法
    @Override
    public Course getCourseById(Integer id) {
        return courseMapper.getCourseById(id);
    }

    //根据条件查询课程
    @Override
    public List<Course> getCourseBySubject(String name, Integer subject, Integer target) {
        List<Course> courseBySubject = courseMapper.getCourseBySubject(name, subject, target);
        return courseBySubject;
    }

    // 分页查询课程
    @Override
    public PageResult<Course> getCourseByPage(String name, Integer subject, Integer target, Integer page, Integer pageSize) {
        PageHelper.startPage(page, pageSize);
        PageInfo pageInfo = new PageInfo(courseMapper.getCourseByPage(name, subject, target));
        PageResult<Course> pageResult = new PageResult<>(pageInfo.getTotal(), pageInfo.getList());
        return pageResult;
    }
}
