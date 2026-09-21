package com.djh.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.djh.PageResult;
import com.djh.entity.OperateLog;
import com.djh.mapper.OperateLogMapper;
import com.djh.service.LogService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class LogServiceImpl implements LogService {
    @Autowired
    private OperateLogMapper operateLogMapper;

    //    分页查询
    @Override
    public PageResult<OperateLog> listLogs(String operateUserName, Integer page, Integer pageSize) {
        log.info("查询操作日志列表: operateUserName={}, page={}, pageSize={}", operateUserName, page, pageSize);
        Page<OperateLog> logPage = operateLogMapper.listLogs(new Page<OperateLog>(page, pageSize), operateUserName);
        return new PageResult<OperateLog>(logPage.getTotal(), logPage.getRecords());
    }

    //    清空操作日志
    @Override
    public void clearLogs() {
        log.info("清空操作日志");
        operateLogMapper.delete(null);
    }
}
