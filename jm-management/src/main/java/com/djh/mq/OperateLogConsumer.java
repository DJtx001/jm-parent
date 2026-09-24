package com.djh.mq;

import com.djh.entity.OperateLog;
import com.djh.mapper.OperateLogMapper;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.RedisConnectionFailureException;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * 操作日志消费者
 * <p>
 * 定时从 Redis 队列批量拉取日志并一次性插入数据库。
 * <p>
 * 为什么用批量插入：生产者每条日志都单独写库会产生 N 次数据库往返，
 * 消费端攒够一批后用一条 `insert ... values (...),(...)` 落库，把 N 次往返压缩成 1 次。
 * <p>
 * 采用固定延迟（fixedDelay）而不是固定频率（fixedRate）：上一轮消费结束后再等指定时间，
 * 避免任务执行时间过长导致任务堆积。
 */
@Slf4j
@Component
public class OperateLogConsumer {

    /**
     * 单批最多消费的条数：太小起不到批量效果，太大 SQL 过长
     */
    private static final int BATCH_SIZE = 100;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private OperateLogMapper operateLogMapper;

    /**
     * 上一轮是否因 Redis 不可用而失败。
     * 用于只在「故障发生」和「故障恢复」的瞬间各记一条日志，
     * 避免 Redis 长时间不可用时每轮都刷一次异常堆栈。
     */
    private volatile boolean redisUnavailable = false;

    /**
     * 批量消费操作日志队列
     * <p>消费间隔可通过 operate.log.consume-interval-ms 配置，默认 5 秒
     */
    @Scheduled(fixedDelayString = "${operate.log.consume-interval-ms:5000}")
    public void consume() {
        // 1. 先从队列取消息。Redis 不可用属于环境故障，单独处理，不打印完整堆栈
        List<String> messages;
        try {
            // RPOP key count：一次最多取 BATCH_SIZE 条，队列为空时返回空集合
            messages = stringRedisTemplate.opsForList()
                    .rightPop(OperateLogProducer.LOG_QUEUE_KEY, BATCH_SIZE);
            if (redisUnavailable) {
                redisUnavailable = false;
                log.info("Redis 已恢复，操作日志消费继续");
            }
        } catch (RedisConnectionFailureException e) {
            if (!redisUnavailable) {
                redisUnavailable = true;
                log.warn("Redis 暂不可用，操作日志消费暂停（恢复后自动继续）: {}", e.getMessage());
            }
            return;
        } catch (Exception e) {
            log.error("操作日志消费失败（读取队列异常）", e);
            return;
        }

        if (messages == null || messages.isEmpty()) {
            return;
        }

        // 2. 反序列化 + 批量落库。这部分的异常才是真正需要完整堆栈定位的问题
        try {
            List<OperateLog> logs = new ArrayList<>(messages.size());
            for (String message : messages) {
                try {
                    logs.add(objectMapper.readValue(message, OperateLog.class));
                } catch (Exception e) {
                    // 单条消息格式异常不影响整批消费
                    log.warn("操作日志反序列化失败，已跳过该条: {}", e.getMessage());
                }
            }
            if (logs.isEmpty()) {
                return;
            }

            operateLogMapper.insertBatch(logs);
            log.info("操作日志异步落库完成，本批 {} 条", logs.size());
        } catch (Exception e) {
            log.error("操作日志落库失败，本批 {} 条", messages.size(), e);
        }
    }
}
