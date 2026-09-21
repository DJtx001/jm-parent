package com.djh.controller;


import com.djh.Result;
import com.djh.service.ReportService;
import com.djh.vo.OverviewVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/report")
public class ReportController {

    @Autowired
    private ReportService reportService;

    /**
     * 获取首页概览数据 - /report/overview
     */
    @GetMapping("/overview")
    public Result getOverview() {
        log.info("获取首页概览数据");
        OverviewVO overview = reportService.getOverview();
        return Result.success(overview);
    }

}