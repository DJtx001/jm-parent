package com.djh.mq;

import com.djh.entity.OperateLog;
import com.djh.mapper.OperateLogMapper;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
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
     * 批量消费操作日志队列
     * <p>消费间隔可通过 operate.log.consume-interval-ms 配置，默认 5 秒
     */
    @Scheduled(fixedDelayString = "${operate.log.consume-interval-ms:5000}")
    public void consume() {
        try {
            // RPOP key count：一次最多取 BATCH_SIZE 条，队列为空时返回空集合
            List<String> messages = stringRedisTemplate.opsForList()
                    .rightPop(OperateLogProducer.LOG_QUEUE_KEY, BATCH_SIZE);
            if (messages == null || messages.isEmpty()) {
                return;
            }

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
            log.error("操作日志消费失败", e);
        }
    }
}
