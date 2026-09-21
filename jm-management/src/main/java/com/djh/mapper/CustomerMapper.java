package com.djh.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.djh.dtp.CustomerQueryDto;
import com.djh.entity.Customer;
import org.apache.ibatis.annotations.Mapper;

/**
 * 客户管理Mapper
 */
@Mapper
public interface CustomerMapper extends BaseMapper<Customer> {
    /**
     * 客户列表分页查询
     */
    Page<Customer> listCustomers(Page<Customer> customerPage, CustomerQueryDto customerQueryDto);
}
