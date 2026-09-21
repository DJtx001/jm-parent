package com.djh.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
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
}