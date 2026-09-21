package com.djh.mapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.djh.entity.Course;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CourseMapper extends BaseMapper<Course> {
////    增加方法
//    @Insert("""
//    INSERT INTO courses
//    (id, subject, name, price, target, description, create_time, update_time)
//    VALUES
//    (null, #{subject}, #{name}, #{price}, #{target}, #{description}, #{createTime}, #{updateTime})
//""")
//    void insertCourse(Course course);
////删除课程
//    @Delete("delete from courses where id = #{id}")
//    void deleteCourseById(Integer id);
////通过Id查询课程
//    @Select("select * from courses where id = #{id}")
//    Course getCourseById(Integer id);
////根据条件查询课程
//    List<Course> getCourseBySubject(String name,Integer subject,Integer target);
////    分页查询课程
//    List<Course> getCourseByPage(String name,Integer subject,Integer target);
////    查询所有课程
//    List<Course> getAllCourse();
//    //修改课程
//    void updateCourseById(Course course);

}
