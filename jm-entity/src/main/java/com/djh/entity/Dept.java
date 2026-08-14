package com.djh.entity;

import jdk.jfr.DataAmount;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Dept {
//    主键id
    private Integer id;
    private String name;
//    部门状态
    private Integer status;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}
