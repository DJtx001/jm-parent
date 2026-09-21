package com.djh.mapper;

import com.djh.entity.Dept;
import org.apache.ibatis.annotations.*;


import java.util.List;

@Mapper
public interface DeptMapper {

    @Insert("insert into department (name, status, create_time, update_time) values(#{name},#{status},#{createTime},#{updateTime}) ")
//    增加部门
    void addDept(Dept dept);

    //    修改部门
    void updateDept(Dept dept);

    //    通过Id查询部门
    @Select("select * from department where id=#{id}")
    Dept getDeptById(Integer id);

    //    分页查找
    List<Dept> getDeptByPage(String name, Integer status);

//    删除 部门
    @Delete("delete from department where id = #{id}")
    void deleteDept(Integer id);
//查询所有 部门
    @Select("select * from department")
    List<Dept> getAllDept();

}