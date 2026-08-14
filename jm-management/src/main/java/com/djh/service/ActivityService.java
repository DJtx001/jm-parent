package com.djh.service;

import com.djh.PageResult;
import com.djh.entity.Activity;

import java.util.List;

public interface ActivityService {
    void insertActivity(Activity activity);

    void deleteActivityById(Integer id);

    void updateActivityById(Activity activity);

    Activity getActivityById(Integer id);

    List<Activity> getActivityByType(Integer channel, Integer type);

    PageResult getActivityByPage(Integer channel, Integer type, Integer status, Integer page, Integer pageSize);
}
