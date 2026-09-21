package com.djh.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.djh.PageResult;
import com.djh.dtp.CustomerQueryDto;
import com.djh.entity.Customer;

public interface CustomerService extends IService<Customer> {
    /**
     * 客户列表
     */
    PageResult<Customer> listCustomers(CustomerQueryDto customerQueryDto);

    /**
     * 新增客户
     */
    void insertCustomer(Customer customer);

    /**
     * 根据Id查询客户
     */
    Customer getCustomerById(Integer id);

    /**
     * 修改客户
     */
    void updateCustomer(Customer customer);
}
