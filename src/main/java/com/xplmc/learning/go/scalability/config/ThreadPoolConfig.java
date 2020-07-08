package com.xplmc.learning.go.scalability.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.ThreadPoolExecutor;

/**
 * configuration for thread pool
 *
 * @author heixiaochun
 * @date 2020-07-08
 */
@Configuration
public class ThreadPoolConfig {

    /**
     * 默认核心线程数
     */
    private static final int DEFAULT_CORE_POOL_SIZE = 10;

    /**
     * 默认最大线程数
     */
    private static final int DEFAULT_MAX_POOL_SIZE = 10;

    /**
     * 默认阻塞队列大小
     */
    private static final int DEFAULT_QUEUE_SIZE = 1000;

    @Bean
    public ThreadPoolTaskExecutor producerThreadPool() {
        return springTaskExecutor("producer-");
    }

    @Bean
    public ThreadPoolTaskExecutor consumerThreadPool() {
        return springTaskExecutor("consumer-");
    }

    /**
     * 创建spring线程池的模板方法
     *
     * @param threadNamePrefix 线程名称前缀
     * @return spring线程池
     */
    private ThreadPoolTaskExecutor springTaskExecutor(String threadNamePrefix) {
        ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();
        taskExecutor.setMaxPoolSize(DEFAULT_MAX_POOL_SIZE);
        taskExecutor.setCorePoolSize(DEFAULT_CORE_POOL_SIZE);
        taskExecutor.setQueueCapacity(DEFAULT_QUEUE_SIZE);
        taskExecutor.setThreadNamePrefix(threadNamePrefix);
        taskExecutor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        taskExecutor.initialize();
        return taskExecutor;
    }

}
