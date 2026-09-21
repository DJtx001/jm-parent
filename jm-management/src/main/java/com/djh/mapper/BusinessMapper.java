package com.djh.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.djh.dtp.BusinessQueryDto;
import com.djh.entity.Business;
import com.djh.vo.OverviewVO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 商机管理Mapper
 */
 @Mapper
public interface BusinessMapper extends BaseMapper<Business> {
 /**
  * 获取商机概览数据
  */
 OverviewVO getBusinessOverviewData();

 /**
  * 商机列表分页查询
  */
 Page<Business> listBusinesses(Page<Business> businessPage, BusinessQueryDto businessQueryDto);

 /**
  * 商机公海池列表分页查询
  */
 Page<Business> listBusinessPool(Page<Business> businessPage, BusinessQueryDto businessQueryDto);

 /**
  * 根据Id查询商机跟进详情
  */
 Business getBusinessById(Integer id);
}