package com.djh.controller;

import com.djh.PageResult;
import com.djh.Result;
import com.djh.dtp.CustomerQueryDto;
import com.djh.entity.Customer;
import com.djh.service.CustomerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/customers")
public class CustomerController {
    @Autowired
    CustomerService customerService;

    /**
     * 列表查询
     */
    @GetMapping
    public Result listCustomers(CustomerQueryDto customerQueryDto) {
        log.info("查询参数: {}", customerQueryDto);
        PageResult<Customer> pageResult = customerService.listCustomers(customerQueryDto);
        return Result.success(pageResult);
    }

    //    新增客户
    @PostMapping()
    public Result insertCustomer(@RequestBody Customer customer) {
        log.info("新增客户: {}", customer);
        customerService.insertCustomer(customer);
        return Result.success();
    }

    //根据ID查询
    @GetMapping("/{id}")
    public Result getCustomerById(@PathVariable Integer id) {
        log.info("根据ID查询客户详细信息, id: {}", id);
        Customer customer = customerService.getCustomerById(id);
        return Result.success(customer);
    }

    //    修改客户
    @PutMapping
    public Result updateCustomer(@RequestBody Customer customer) {
        log.info("修改客户: {}", customer);
        customerService.updateCustomer(customer);
        return Result.success();
    }
}
