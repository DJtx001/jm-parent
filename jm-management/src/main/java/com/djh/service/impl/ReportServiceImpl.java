package com.djh.service.impl;

import com.djh.mapper.BusinessMapper;
import com.djh.mapper.ClueMapper;
import com.djh.service.ReportService;
import com.djh.vo.OverviewVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Slf4j
@Service
public class ReportServiceImpl implements ReportService {

    @Autowired
    private ClueMapper clueMapper;
    @Autowired
    private BusinessMapper businessMapper;
    @Autowired
    private RedisTemplate<Object, Object> redisTemplate;

    @Override
    public OverviewVO getOverview() {
        //1. 查询redis缓存中的数据
        Object dataOverview = redisTemplate.opsForValue().get("DATA_OVERVIEW");
        if(dataOverview != null){
            log.info("查询redis缓存中的数据: {}" , dataOverview);
            return (OverviewVO) dataOverview;
        }
        //1. 获取线索概览数据
        OverviewVO clueOverviewVO = clueMapper.getClueOverviewData();

        //2. 获取商机概览数据
        OverviewVO businessOverviewVO = businessMapper.getBusinessOverviewData();

        //3. 合并数据返回
        BeanUtils.copyProperties(businessOverviewVO, clueOverviewVO, "clueTotal", "clueWaitAllot", "clueWaitFollow", "clueFollowing", "clueFalse", "clueConvertBusiness");
        //3. 缓存数据
        log.info("查询数据库中的数据: {}, 缓存到redis中" , clueOverviewVO);
        redisTemplate.opsForValue().set("DATA_OVERVIEW", clueOverviewVO, 5, TimeUnit.MINUTES);
        return clueOverviewVO;
    }
}