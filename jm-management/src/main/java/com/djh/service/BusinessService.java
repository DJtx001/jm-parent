package com.djh.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.djh.PageResult;
import com.djh.dtp.BusinessQueryDto;
import com.djh.entity.Business;

public interface BusinessService extends IService<Business> {
    /**
     * 商机列表
     */
    PageResult<Business> listBusinesses(BusinessQueryDto businessQueryDto);

    /**
     * 新增商机
     */
    void insertBusiness(Business business);

    /**
     * 分配商机
     */
    void assignBusiness(Integer businessId, Integer userId);

    /**
     * 踢回公海
     */
    void backToPool(Integer id);

    /**
     * 转客户
     */
    void convertToCustomer(Integer id);

    /**
     * 根据Id查询商机详情
     */
    Business getBusinessById(Integer id);

    /**
     * 跟进商机
     */
    void trackBusiness(Business business);

    /**
     * 商机公海池列表
     */
    PageResult<Business> listBusinessPool(BusinessQueryDto businessQueryDto);
}
