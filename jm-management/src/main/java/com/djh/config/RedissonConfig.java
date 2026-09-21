package com.djh.config;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.redisson.config.SingleServerConfig;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;

/**
 * Redisson 配置
 * <p>
 * 复用 Spring Boot 已有的 Redis 连接参数，手动装配 {@link RedissonClient}。
 * 这里刻意不引入 redisson-spring-boot-starter：starter 会注册自己的 RedissonConnectionFactory
 * 并接管（覆盖）Spring Boot 原有基于 Lettuce 的 Redis 连接工厂，可能影响项目里已有的 Redis 使用方式。
 * <p>
 * 标注 {@link Lazy}：Redisson 创建时会**立刻建立连接**，若 Redis 未启动会导致应用启动失败，
 * 延迟初始化可以保证 Redis 暂时不可用时应用仍能正常启动。
 */
@Configuration
public class RedissonConfig {

    @Lazy
    @Bean(destroyMethod = "shutdown")
    public RedissonClient redissonClient(RedisProperties redisProperties) {
        Config config = new Config();
        SingleServerConfig serverConfig = config.useSingleServer()
                .setAddress("redis://" + redisProperties.getHost() + ":" + redisProperties.getPort())
                .setDatabase(redisProperties.getDatabase());
        String password = redisProperties.getPassword();
        if (password != null && !password.isEmpty()) {
            serverConfig.setPassword(password);
        }
        return Redisson.create(config);
    }
}
