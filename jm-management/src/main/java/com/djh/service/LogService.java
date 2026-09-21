package com.djh.service;

import com.djh.PageResult;
import com.djh.entity.OperateLog;

public interface LogService {
    /**
     * 操作日志列表
     */
    PageResult<OperateLog> listLogs(String operateUserName, Integer page, Integer pageSize);

    /**
     * 清空操作日志
     */
    void clearLogs();
}
