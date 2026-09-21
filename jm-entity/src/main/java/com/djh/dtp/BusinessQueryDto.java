package com.djh.dtp;

import lombok.Data;

/**
 * 商机查询参数封装类
 */
@Data
public class BusinessQueryDto {

    /**
     * 商机ID
     */
    private Integer businessId;

    /**
     * 客户姓名
     */
    private String name;

    /**
     * 手机号
     */
    private String phone;

    /**
     * 意向学科，1:AI智能应用开发(java), 2:AI大模型开发(python)，3:AI鸿蒙开发，4:AI大数据，5:AI嵌入式，6:AI测试，7:AI运维
     */
    private Integer subject;

    /**
     * 商机状态，1:待分配, 2:待跟进, 3:跟进中, 4:回收, 5:转客户
     */
    private Integer status;

    /**
     * 分页查询的页码，如果未指定，默认为1
     */
    private Integer page = 1;

    /**
     * 分页查询的每页记录数，如果未指定，默认为10
     */
    private Integer pageSize = 10;
}
