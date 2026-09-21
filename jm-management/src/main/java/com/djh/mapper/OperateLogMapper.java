package com.djh.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.djh.entity.OperateLog;
import org.apache.ibatis.annotations.Mapper;

/**
 * 操作日志管理Mapper
 */
@Mapper
public interface OperateLogMapper extends BaseMapper<OperateLog> {
}