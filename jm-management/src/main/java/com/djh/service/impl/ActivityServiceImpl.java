package com.djh.service.impl;

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
public class ActivityServiceImpl implements ActivityService {
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
    }
//根据id查询活动
    @Override
    public Activity getActivityById(Integer id) {
        return activityMapper.getActivityById(id);
    }
//根据类型查询活动
    @Override
    public List<Activity> getActivityByType(Integer channel, Integer type) {
        List<Activity> activityByType = activityMapper.getActivityByType(channel, type);
        return activityByType;
    }
//根据页码查询活动
    @Override
    public PageResult getActivityByPage(Integer channel, Integer type, Integer status, Integer page, Integer pageSize) {
        PageHelper.startPage(page, pageSize);
        PageInfo pageIfo = new PageInfo<>(activityMapper.getActivityByPage(channel, type, status));
        PageResult<Activity> pageResult = new PageResult<>(pageIfo.getTotal(), pageIfo.getList());
        return pageResult;
    }
}
