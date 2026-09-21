package com.djh.mapper;

import com.djh.entity.User;
import com.djh.dtp.UserDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface UserMapper {
    //    分页查找
    List<User> getUserByPage(UserDto userDto);

    //添加用户
    void insertUser(User user);
//    删除 用户可以批量删除
    void deleteUser(List<Integer> ids);
//    修改 用户
    void updateUser(User user);
    /**
     * 根据用户名查询用户信息
     */
    @Select("SELECT u.*, r.label as role_label FROM user u LEFT JOIN role r ON u.role_id = r.id WHERE u.username = #{username}")
    User selectByUsername(String username);
}
