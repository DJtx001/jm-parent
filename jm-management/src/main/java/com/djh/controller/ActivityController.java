package com.djh.controller;

import com.aliyun.oss.AliyunOSSOperator;
import com.djh.PageResult;
import com.djh.Result;
import com.djh.entity.Activity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.djh.service.ActivityService;

import java.util.List;

@RestController
public class ActivityController {
    //    依赖注入
    @Autowired
    private ActivityService activityService;
    @Autowired
    private AliyunOSSOperator aliyunOSSOperator;

    //根据条件查询活动 分页查询
    @GetMapping("/activities")
    public Result getActivityByPage(@RequestParam(required = false) Integer channel,
                                    @RequestParam(required = false) Integer type,
                                    @RequestParam(required = false) Integer status,
                                    @RequestParam(required = false, defaultValue = "1") Integer page,
                                    @RequestParam(required = false, defaultValue = "10") Integer pageSize
    ) {
        PageResult<Activity> pageResult = activityService.getActivityByPage(channel, type, status, page, pageSize);
        return Result.success(pageResult);
    }

    //根据id删除活动
    @DeleteMapping("/activities/{id}")
    public Result deleteActivityById(@PathVariable Integer id) {
        activityService.deleteActivityById(id);
        return Result.success();
    }

    //增加 活动
    @PostMapping("/activities")
    public Result insertActivity(@RequestBody Activity activity) {
        activityService.insertActivity(activity);
        return Result.success();
    }

    //通过id查询活动
    @GetMapping("/activities/{id}")
    public Result getActivityById(@PathVariable Integer id) {
        Activity activityById = activityService.getActivityById(id);
        return Result.success(activityById);
    }

    //修改活动
    @PutMapping("/activities")
    public Result updateActivityById(@RequestBody Activity activity) {
        activityService.updateActivityById(activity);
        return Result.success();
    }

    //根据类型查询活动
    @GetMapping("/activities/type/{type}")
    public Result getActivityByType(@PathVariable Integer type) {
        List<Activity> activityByType = activityService.getActivityByType(type);
        return Result.success(activityByType);
    }
}
