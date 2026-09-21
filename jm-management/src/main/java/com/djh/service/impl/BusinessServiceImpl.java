package com.djh.service.impl;

import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.djh.PageResult;
import com.djh.dtp.BusinessQueryDto;
import com.djh.entity.Business;
import com.djh.entity.BusinessTrackRecord;
import com.djh.entity.Customer;
import com.djh.exception.BusinessException;
import com.djh.mapper.BusinessMapper;
import com.djh.mapper.BusinessTrackRecordMapper;
import com.djh.mapper.CustomerMapper;
import com.djh.service.BusinessService;
import com.djh.utils.CurrentUserHoler;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
public class BusinessServiceImpl extends ServiceImpl<BusinessMapper, Business> implements BusinessService {
    @Autowired
    private BusinessTrackRecordMapper businessTrackRecordMapper;

    @Autowired
    private CustomerMapper customerMapper;

    // 分页查询
    @Override
    public PageResult<Business> listBusinesses(BusinessQueryDto businessQueryDto) {
        Page<Business> page = this.baseMapper.listBusinesses(new Page<Business>(businessQueryDto.getPage(), businessQueryDto.getPageSize()), businessQueryDto);
        return new PageResult<Business>(page.getTotal(), page.getRecords());
    }

    // 分页查询公海池
    @Override
    public PageResult<Business> listBusinessPool(BusinessQueryDto businessQueryDto) {
        Page<Business> page = this.baseMapper.listBusinessPool(new Page<Business>(businessQueryDto.getPage(), businessQueryDto.getPageSize()), businessQueryDto);
        return new PageResult<Business>(page.getTotal(), page.getRecords());
    }

    // 新增商机
    @Override
    public void insertBusiness(Business business) {
        LocalDateTime now = LocalDateTime.now();
        business.setStatus(1); // 待分配
        business.setCreateTime(now);
        business.setUpdateTime(now);
        this.save(business);
    }

    // 分配商机
    @Override
    public void assignBusiness(Integer businessId, Integer userId) {
        Business existBusiness = this.getById(businessId);
        if (existBusiness == null) {
            throw new BusinessException("商机不存在, id=" + businessId);
        }

        // 用新对象只更新必要字段，避免前端误传字段覆盖原值
        Business updateBusiness = new Business();
        updateBusiness.setId(businessId);
        updateBusiness.setUserId(userId); // 设置归属人ID
        updateBusiness.setStatus(2); // 待跟进
        updateBusiness.setUpdateTime(LocalDateTime.now());
        this.updateById(updateBusiness);
    }

    // 踢回公海
    @Override
    public void backToPool(Integer id) {
        Business existBusiness = this.getById(id);
        if (existBusiness == null) {
            throw new BusinessException("商机不存在, id=" + id);
        }

        // 归属人需要置空，用 UpdateWrapper 显式设置为 null
        this.update(new LambdaUpdateWrapper<Business>()
                .eq(Business::getId, id)
                .set(Business::getStatus, 4) // 回收
                .set(Business::getUserId, null)
                .set(Business::getUpdateTime, LocalDateTime.now()));
    }

    // 转客户
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void convertToCustomer(Integer id) {
        // 1. 查询原商机，确认存在
        Business business = this.getById(id);
        if (business == null) {
            throw new BusinessException("商机不存在, id=" + id);
        }

        LocalDateTime now = LocalDateTime.now();

        // 2. 更新商机状态
        Business updateBusiness = new Business();
        updateBusiness.setId(id);
        updateBusiness.setStatus(5); // 转客户
        updateBusiness.setUpdateTime(now);
        this.updateById(updateBusiness);

        // 3. 创建客户信息
        Customer customer = new Customer();
        BeanUtils.copyProperties(business, customer);
        customer.setId(null); // 设置客户ID为空, 表示新增客户, 主键自动增长
        customer.setBusinessId(id); // 来源商机id
        customer.setCreateTime(now);
        customer.setUpdateTime(now);

        // 4. 插入客户记录
        customerMapper.insert(customer);
    }

    // 根据Id查询
    @Override
    public Business getBusinessById(Integer id) {
        return this.baseMapper.getBusinessById(id);
    }

    // 跟进商机：更新商机状态 + 新增跟进记录
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void trackBusiness(Business business) {
        // 1. 参数校验
        if (business == null || business.getId() == null) {
            throw new BusinessException("商机ID不能为空");
        }

        // 2. 查询原商机，确认存在
        Business existBusiness = this.getById(business.getId());
        if (existBusiness == null) {
            throw new BusinessException("商机不存在, id=" + business.getId());
        }

        LocalDateTime now = LocalDateTime.now();

        // 3. 更新商机（参考 Business.java 状态约定：1待分配, 2待跟进, 3跟进中, 4回收, 5转客户）
        //    用新对象只更新必要字段，避免前端误传字段覆盖原值
        Business updateBusiness = new Business();
        updateBusiness.setId(business.getId());
        updateBusiness.setStatus(3); // 跟进中
        updateBusiness.setNextTime(business.getNextTime());
        updateBusiness.setUpdateTime(now);
        this.updateById(updateBusiness);

        // 4. 新增跟进记录
        BusinessTrackRecord trackRecord = new BusinessTrackRecord();
        trackRecord.setBusinessId(business.getId());
        trackRecord.setUserId(CurrentUserHoler.getCurrentUser()); //当前登录用户ID
        trackRecord.setTrackStatus(business.getTrackStatus());
        trackRecord.setKeyItems(business.getKeyItems() == null ? null : business.getKeyItems().toString());
        trackRecord.setNextTime(business.getNextTime());
        trackRecord.setRecord(business.getRecord());
        trackRecord.setCreateTime(now);
        businessTrackRecordMapper.insert(trackRecord);
    }
}
