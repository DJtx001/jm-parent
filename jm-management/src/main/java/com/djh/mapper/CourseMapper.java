package com.djh.mapper;
import com.djh.entity.Course;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface CourseMapper {
//    增加方法
    @Insert("""
    INSERT INTO courses
    (id, subject, name, price, target, description, create_time, update_time)
    VALUES
    (null, #{subject}, #{name}, #{price}, #{target}, #{description}, #{createTime}, #{updateTime})
""")
    void insertCourse(Course course);
//删除课程
    @Delete("delete from courses where id = #{id}")
    void deleteCourseById(Integer id);
//通过Id查询课程
    @Select("select * from courses where id = #{id}")
    Course getCourseById(Integer id);
//根据条件查询课程
    List<Course> getCourseBySubject(String name,Integer subject,Integer target);
//    分页查询课程
    List<Course> getCourseByPage(String name,Integer subject,Integer target);
}
