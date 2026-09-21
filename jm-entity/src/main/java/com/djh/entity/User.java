package com.djh.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.time.LocalDateTime;

/**
 * 用户实体类
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    /**
     * 用户ID，主键
     */
    private Integer id;

    /**
     * 用户名，唯一
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 姓名
     */
    private String name;

    /**
     * 手机号，唯一
     */
    private String phone;

    /**
     * 邮箱，唯一
     */
    private String email;

    /**
     * 性别，1: 男，2: 女
     */
    private Integer gender;

    /**
     * 状态，1: 正常，0: 停用
     */
    private Integer status;

    /**
     * 部门ID，关联部门表主键
     */
    private Integer deptId;

    /**
     * 角色ID，关联角色表主键
     */
    private Integer roleId;

    /**
     * 头像URL
     */
    private String image;

    /**
     * 备注，50字以内
     */
    private String remark;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 修改时间
     */
    private LocalDateTime updateTime;

    //扩展属性
    private String deptName; //部门名称
    private String roleName; //角色名称
//    角色标签
    private String roleLabel;
}