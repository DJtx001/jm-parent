package com.djh.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.djh.PageResult;
import com.djh.entity.Course;
import com.djh.mapper.CourseMapper;
import com.djh.service.CourseService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
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
        courseMapper.insert(course);
    }

    //通过id删除课程
    @Override
    public void deleteCourseById(Integer id) {
        courseMapper.deleteById(id);
    }


    //根据id查询方法
    @Override
    public Course getCourseById(Integer id) {
        return courseMapper.selectById(id);
    }



    // 分页查询课程
    @Override
    public PageResult<Course> getCourseByPage(String name, Integer subject, Integer target, Integer page, Integer pageSize) {
        PageHelper.startPage(page, pageSize);


//        QueryWrapper<Course> queryWrapper = new QueryWrapper<>();
        LambdaQueryWrapper<Course> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.like(name != null && !name.isEmpty(), Course::getName, name);
        queryWrapper.eq(subject != null, Course::getSubject, subject);
        queryWrapper.eq(target != null, Course::getTarget, target);
        List<Course> courses = courseMapper.selectList(queryWrapper);


        Page<Course> pageInfo = (Page<Course>) courses;
        return new PageResult<>(pageInfo.getTotal(), pageInfo.getResult());
    }
//查询所有课程
    @Override
    public List<Course> getAllCourse() {
        return courseMapper.selectList(null);
    }
//修改课程
    @Override
    public void updateCourseById(Course course) {
       courseMapper.updateById(course);
       course.setUpdateTime(LocalDateTime.now());
    }
}
