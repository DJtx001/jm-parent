package com.djh.controller;

import com.djh.PageResult;
import com.djh.Result;
import com.djh.entity.OperateLog;
import com.djh.service.LogService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class LogController {
    @Autowired
    LogService logService;

    /**
     * 列表查询
     */
    @GetMapping("/logs")
    public Result listLogs(@RequestParam(required = false) String operateUserName,
                           @RequestParam(required = false, defaultValue = "1") Integer page,
                           @RequestParam(required = false, defaultValue = "10") Integer pageSize) {
        log.info("查询日志列表: 操作人={}, 页码={}, 每页条数={}", operateUserName, page, pageSize);
        PageResult<OperateLog> pageResult = logService.listLogs(operateUserName, page, pageSize);
        return Result.success(pageResult);
    }

    /**
     * 清空日志
     */
    @DeleteMapping("/logs")
    public Result clearLogs() {
        log.info("清空日志");
        logService.clearLogs();
        return Result.success();
    }
}
