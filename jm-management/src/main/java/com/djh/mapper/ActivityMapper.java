package com.djh.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.djh.entity.Activity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ActivityMapper extends BaseMapper<Activity> {
//    增加活动
    void insertActivity(Activity activity);
//根据id删除活动
    void deleteActivityById(Integer id);
//修改活动
    void updateActivityById(Activity activity);
//根据id查询活动
    Activity getActivityById(Integer id);
//根据渠道和类型查询活动
    List<Activity> getActivityByType(Integer type);
//根据页码查询活动
    List<Activity> getActivityByPage(Integer channel, Integer type);
}
