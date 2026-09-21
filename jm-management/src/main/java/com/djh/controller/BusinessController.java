package com.djh.controller;

import com.djh.PageResult;
import com.djh.Result;
import com.djh.dtp.BusinessQueryDto;
import com.djh.entity.Business;
import com.djh.service.BusinessService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/businesses")
public class BusinessController {
    @Autowired
    private BusinessService businessService;

    /**
     * 商机列表查询
     */
    @GetMapping
    public Result listBusinesses(BusinessQueryDto businessQueryDto) {
        log.info("查询参数: {}", businessQueryDto);
        PageResult<Business> pageResult = businessService.listBusinesses(businessQueryDto);
        return Result.success(pageResult);
    }

    /**
     * 新增商机
     */
    @PostMapping
    public Result insertBusiness(@RequestBody Business business) {
        log.info("新增商机: {}", business);
        businessService.insertBusiness(business);
        return Result.success();
    }

    /**
     * 分配商机
     */
    @PutMapping("/assign/{businessId}/{userId}")
    public Result assignBusiness(@PathVariable Integer businessId, @PathVariable Integer userId) {
        log.info("分配商机: 商机ID={}, 用户ID={}", businessId, userId);
        businessService.assignBusiness(businessId, userId);
        return Result.success();
    }

    /**
     * 踢回公海
     */
    @PutMapping("/back/{id}")
    public Result backToPool(@PathVariable Integer id) {
        log.info("将商机踢回公海, id: {}", id);
        businessService.backToPool(id);
        return Result.success();
    }

    /**
     * 转客户
     */
    @PostMapping("/toCustomer/{id}")
    public Result convertToCustomer(@PathVariable Integer id) {
        log.info("将商机转为客户, id: {}", id);
        businessService.convertToCustomer(id);
        return Result.success();
    }

    /**
     * 商机公海池列表查询
     */
    @GetMapping("/pool")
    public Result listBusinessPool(BusinessQueryDto businessQueryDto) {
        log.info("查询公海池参数: {}", businessQueryDto);
        PageResult<Business> pageResult = businessService.listBusinessPool(businessQueryDto);
        return Result.success(pageResult);
    }

    /**
     * 根据ID查询商机详情
     */
    @GetMapping("/{id}")
    public Result getBusinessById(@PathVariable Integer id) {
        log.info("根据ID查询商机详细信息, id: {}", id);
        Business business = businessService.getBusinessById(id);
        return Result.success(business);
    }

    /**
     * 跟进商机
     */
    @PutMapping
    public Result trackBusiness(@RequestBody Business business) {
        log.info("跟进商机: {}", business);
        businessService.trackBusiness(business);
        return Result.success();
    }
}
