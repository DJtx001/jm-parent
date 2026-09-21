package com.djh.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.djh.PageResult;
import com.djh.entity.Activity;
import com.djh.mapper.ActivityMapper;
import com.djh.service.ActivityService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ActivityServiceImpl extends ServiceImpl<ActivityMapper,Activity> implements ActivityService {
    //    注入映射器
    @Autowired
    private ActivityMapper activityMapper;
//增加 活动
    @Override
    public void insertActivity(Activity activity) {
        LocalDateTime now = LocalDateTime.now();
        activity.setCreateTime(now);
        activity.setUpdateTime(now);
        activityMapper.insertActivity(activity);
    }
//删除活动
    @Override
    public void deleteActivityById(Integer id) {
        activityMapper.deleteActivityById(id);
    }
//修改活动
    @Override
    public void updateActivityById(Activity activity) {
        activityMapper.updateActivityById(activity);
        activity.setUpdateTime(LocalDateTime.now());
    }
//根据id查询活动
    @Override
    public Activity getActivityById(Integer id) {
        return activityMapper.getActivityById(id);
    }
//根据类型查询活动
    @Override
    public List<Activity> getActivityByType(Integer type) {
        List<Activity> activityByType = activityMapper.getActivityByType(type);
        return activityByType;
    }
//根据页码查询活动
    @Override
    public PageResult getActivityByPage(Integer type, Integer status, Integer page, Integer pageSize) {
        PageHelper.startPage(page, pageSize);
        LambdaQueryWrapper<Activity> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(type != null, Activity::getType, type)
        .gt(status != null && status == 1, Activity::getStartTime, LocalDateTime.now())
                .le(status != null && status == 2, Activity::getStartTime, LocalDateTime.now())
                .ge(status != null && status == 2, Activity::getEndTime, LocalDateTime.now())
                .lt(status != null && status == 3, Activity::getEndTime, LocalDateTime.now())
                .orderByDesc(Activity::getUpdateTime);
        Page p = page(new Page(page, pageSize), queryWrapper);
        return new PageResult<>(p.getTotal(), p.getRecords());
    }
}
