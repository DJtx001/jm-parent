package com.djh.task;

import com.djh.service.ClueService;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * 线索自动回收定时任务
 * <p>
 * <b>业务背景</b>：线索被分配给销售后，如果长期没有跟进，会一直占着归属人不放，其他销售也无法接手。
 * 因此需要一个定时任务，把「超时未跟进」的线索回收到线索池（状态改为待分配、清空归属人）。
 * <p>
 * <b>分布式锁</b>：定时任务在多实例部署时会同时触发，用 Redisson 的 RLock 保证同一时刻
 * 只有一个实例真正执行，避免重复回收。
 */
@Slf4j
@Component
public class ClueRecycleTask {

    /**
     * 分布式锁的 key
     */
    private static final String LOCK_KEY = "lock:clue:recycle";

    /**
     * 锁的租期（秒）：兜底防止业务异常导致锁永远无法释放
     */
    private static final long LOCK_LEASE_SECONDS = 60L;

    @Autowired
    private ClueService clueService;

    /**
     * Redisson 客户端延迟注入，避免 Redis 未启动时应用启动失败
     */
    @Lazy
    @Autowired
    private RedissonClient redissonClient;

    /**
     * 超过多少天未跟进则回收
     */
    @Value("${clue.recycle.threshold-days:7}")
    private int thresholdDays;

    /**
     * 定时回收超时未跟进的线索
     * <p>cron 表达式可在 application.yml 中通过 clue.recycle.cron 配置
     */
    @Scheduled(cron = "${clue.recycle.cron:0 0 2 * * ?}")
    public void recycleTimeoutClues() {
        try {
            RLock lock = redissonClient.getLock(LOCK_KEY);
            // 等待时间传 0：拿不到锁立即返回，不阻塞线程；同时设置租期防止死锁
            boolean locked = lock.tryLock(0, LOCK_LEASE_SECONDS, TimeUnit.SECONDS);
            if (!locked) {
                log.info("线索自动回收任务未获取到分布式锁（其他实例正在执行），跳过本次");
                return;
            }
            try {
                int count = clueService.recycleTimeoutClues(thresholdDays);
                log.info("线索自动回收任务执行完成，共同收 {} 条超过 {} 天未跟进的线索", count, thresholdDays);
            } finally {
                // 只允许锁的持有者释放，避免误删其他实例持有的锁
                if (lock.isHeldByCurrentThread()) {
                    lock.unlock();
                }
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            log.warn("线索自动回收任务被中断");
        } catch (Exception e) {
            log.error("线索自动回收任务执行异常", e);
        }
    }
}
