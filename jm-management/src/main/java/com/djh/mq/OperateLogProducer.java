package com.djh.mq;

import com.djh.entity.OperateLog;
import com.djh.mapper.OperateLogMapper;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

/**
 * 操作日志生产者
 * <p>
 * 把日志消息投递到 Redis 队列（List 结构），由消费者异步批量落库，实现：
 * <ul>
 *   <li><b>异步解耦</b>：业务主流程不再等待日志写库，接口响应更快</li>
 *   <li><b>削峰填谷</b>：突发流量下日志先堆积在队列里，消费者按自己的节奏批量消费</li>
 * </ul>
 * 消息体使用 JSON 序列化（而不是 JDK 序列化），保证可读、可跨语言、便于排查。
 * <p>
 * 说明：本项目用 Redis List 实现轻量消息队列（零新增中间件）。若后续要换成 RabbitMQ，
 * 只需替换本类的投递方法和 {@link OperateLogConsumer} 的消费方法，业务代码无需改动。
 */
@Slf4j
@Component
public class OperateLogProducer {

    /**
     * 操作日志队列 key（Redis List：LPUSH 入队，RPOP 出队，先进先出）
     */
    public static final String LOG_QUEUE_KEY = "QUEUE:OPERATE_LOG";

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private OperateLogMapper operateLogMapper;

    /**
     * 投递操作日志到队列
     * <p>队列不可用时降级为同步落库：既保证日志不丢，也保证业务主流程不受影响
     */
    public void send(OperateLog operateLog) {
        try {
            String message = objectMapper.writeValueAsString(operateLog);
            stringRedisTemplate.opsForList().leftPush(LOG_QUEUE_KEY, message);
        } catch (Exception e) {
            log.warn("操作日志投递队列失败，降级为同步落库: {}", e.getMessage());
            try {
                operateLogMapper.insert(operateLog);
            } catch (Exception ex) {
                log.error("操作日志同步落库失败", ex);
            }
        }
    }
}
