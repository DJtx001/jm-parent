package com.djh.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.djh.entity.OperateLog;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 操作日志管理Mapper
 */
@Mapper
public interface OperateLogMapper extends BaseMapper<OperateLog> {
    /**
     * 操作日志列表分页查询
     */
    Page<OperateLog> listLogs(Page<OperateLog> page, @Param("operateUserName") String operateUserName);

    /**
     * 批量插入操作日志
     * <p>一条 SQL 插入多条，把 N 次数据库往返压缩成 1 次，配合异步队列起到削峰作用
     */
    int insertBatch(@Param("list") List<OperateLog> logs);
}
