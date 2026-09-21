package com.djh.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.djh.PageResult;
import com.djh.dtp.CustomerQueryDto;
import com.djh.entity.Customer;
import com.djh.mapper.CustomerMapper;
import com.djh.service.CustomerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Slf4j
@Service
public class CustomerServiceImpl extends ServiceImpl<CustomerMapper, Customer> implements CustomerService {

    //    分页查询
    @Override
    public PageResult<Customer> listCustomers(CustomerQueryDto customerQueryDto) {
        Page<Customer> page = this.baseMapper.listCustomers(new Page<Customer>(customerQueryDto.getPage(), customerQueryDto.getPageSize()), customerQueryDto);
        return new PageResult<Customer>(page.getTotal(), page.getRecords());
    }

    //    新增客户
    @Override
    public void insertCustomer(Customer customer) {
        customer.setCreateTime(LocalDateTime.now());
        customer.setUpdateTime(LocalDateTime.now());
        this.save(customer);
    }

    //    根据Id查询
    @Override
    public Customer getCustomerById(Integer id) {
        return this.baseMapper.selectById(id);
    }

    //    修改客户
    @Override
    public void updateCustomer(Customer customer) {
        customer.setUpdateTime(LocalDateTime.now());
        this.updateById(customer);
    }
}
