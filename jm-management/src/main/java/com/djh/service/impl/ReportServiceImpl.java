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

import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

/**
 * 首页概览统计
 * <p>
 * 概览数据需要跨表聚合统计（线索 + 商机各状态数量），属于「计算耗时、变化不频繁」的典型缓存场景。
 * <p>
 * 这里对缓存做了三层防护，分别应对缓存三大经典问题：
 * <ul>
 *   <li><b>缓存穿透</b>：数据库查不到数据时缓存一个空值占位符（短过期），避免请求反复打到数据库</li>
 *   <li><b>缓存击穿</b>：热点 key 失效瞬间，用 Redis 互斥锁保证只有一个线程去查库重建缓存</li>
 *   <li><b>缓存雪崩</b>：过期时间加随机秒数，打散失效时刻，避免大量缓存同一时间集体失效</li>
 * </ul>
 */
@Slf4j
@Service
public class ReportServiceImpl implements ReportService {

    /**
     * 概览数据缓存 key
     */
    private static final String CACHE_KEY = "DATA_OVERVIEW";

    /**
     * 重建缓存用的互斥锁 key（防击穿）
     */
    private static final String REBUILD_LOCK_KEY = "LOCK:DATA_OVERVIEW:REBUILD";

    /**
     * 空值占位符：数据库确实没有数据时缓存它（防穿透）
     */
    private static final String NULL_VALUE_PLACEHOLDER = "__NULL__";

    /**
     * 缓存基础过期时间（分钟）
     */
    private static final long BASE_EXPIRE_MINUTES = 5L;

    /**
     * 过期时间随机上浮范围（秒）：打散到期时刻（防雪崩）
     */
    private static final int EXPIRE_RANDOM_BOUND_SECONDS = 60;

    /**
     * 空值占位符的过期时间（秒）：比正常缓存短，避免真实数据恢复后长时间读到空值
     */
    private static final long NULL_VALUE_EXPIRE_SECONDS = 30L;

    /**
     * 互斥锁持有时间（秒）：兜底防止持锁线程异常导致锁无法释放
     */
    private static final long LOCK_LEASE_SECONDS = 10L;

    /**
     * 未抢到锁时的等待重试次数与间隔（毫秒）
     */
    private static final int WAIT_RETRY_TIMES = 5;
    private static final long WAIT_RETRY_INTERVAL_MILLIS = 50L;

    @Autowired
    private ClueMapper clueMapper;
    @Autowired
    private BusinessMapper businessMapper;
    @Autowired
    private RedisTemplate<Object, Object> redisTemplate;

    @Override
    public OverviewVO getOverview() {
        // 1. 先读缓存（读到空值占位符同样算命中，直接返回空结果）
        Object cached = readCache();
        if (cached != null) {
            log.info("命中概览缓存");
            return resolve(cached);
        }

        // 2. 缓存未命中：抢互斥锁，保证同一时刻只有一个线程去查库重建缓存（防击穿）
        Boolean locked = redisTemplate.opsForValue()
                .setIfAbsent(REBUILD_LOCK_KEY, "1", LOCK_LEASE_SECONDS, TimeUnit.SECONDS);
        if (Boolean.TRUE.equals(locked)) {
            try {
                // 双重检查：抢锁期间缓存可能已被其他线程重建好了
                cached = readCache();
                if (cached != null) {
                    return resolve(cached);
                }
                log.info("缓存未命中，查询数据库并重建缓存");
                return loadFromDbAndCache();
            } finally {
                redisTemplate.delete(REBUILD_LOCK_KEY);
            }
        }

        // 3. 没抢到锁：短暂自旋等待持锁线程重建完成，避免所有线程同时涌向数据库
        for (int i = 0; i < WAIT_RETRY_TIMES; i++) {
            sleepQuietly();
            cached = readCache();
            if (cached != null) {
                log.info("等待其他线程重建缓存后命中");
                return resolve(cached);
            }
        }

        // 4. 等待超时兜底：自己查库返回，但不写缓存（写缓存交给持锁线程，避免覆盖）
        log.warn("等待缓存重建超时，本次直接查库返回");
        return mergeOverviewData();
    }

    /**
     * 读取缓存原始值，返回 null 表示未命中
     */
    private Object readCache() {
        return redisTemplate.opsForValue().get(CACHE_KEY);
    }

    /**
     * 解析缓存值：空值占位符返回 null，否则强转为 {@link OverviewVO}
     */
    private OverviewVO resolve(Object cached) {
        if (NULL_VALUE_PLACEHOLDER.equals(cached)) {
            log.info("命中空值占位符，直接返回空结果");
            return null;
        }
        return (OverviewVO) cached;
    }

    /**
     * 查询数据库并写入缓存
     */
    private OverviewVO loadFromDbAndCache() {
        OverviewVO overview = mergeOverviewData();
        if (overview == null) {
            // 数据库确实没有数据：缓存空值占位符（短过期），防止请求反复穿透到数据库
            redisTemplate.opsForValue()
                    .set(CACHE_KEY, NULL_VALUE_PLACEHOLDER, NULL_VALUE_EXPIRE_SECONDS, TimeUnit.SECONDS);
            return null;
        }
        // 防雪崩：基础过期时间 + 随机秒数，避免大量缓存在同一时刻集体失效
        long expireSeconds = TimeUnit.MINUTES.toSeconds(BASE_EXPIRE_MINUTES)
                + ThreadLocalRandom.current().nextInt(EXPIRE_RANDOM_BOUND_SECONDS);
        redisTemplate.opsForValue().set(CACHE_KEY, overview, expireSeconds, TimeUnit.SECONDS);
        return overview;
    }

    /**
     * 查库并合并线索、商机两部分统计数据
     */
    private OverviewVO mergeOverviewData() {
        OverviewVO clueOverviewVO = clueMapper.getClueOverviewData();
        OverviewVO businessOverviewVO = businessMapper.getBusinessOverviewData();

        if (clueOverviewVO == null && businessOverviewVO == null) {
            return null;
        }
        if (clueOverviewVO == null) {
            return businessOverviewVO;
        }
        if (businessOverviewVO != null) {
            // 商机统计合并进线索统计对象，线索字段保持不变
            BeanUtils.copyProperties(businessOverviewVO, clueOverviewVO,
                    "clueTotal", "clueWaitAllot", "clueWaitFollow", "clueFollowing", "clueFalse", "clueConvertBusiness");
        }
        return clueOverviewVO;
    }

    private void sleepQuietly() {
        try {
            Thread.sleep(WAIT_RETRY_INTERVAL_MILLIS);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
