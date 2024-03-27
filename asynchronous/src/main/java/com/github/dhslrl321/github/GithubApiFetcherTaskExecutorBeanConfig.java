package com.github.dhslrl321.github;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@Configuration
public class GithubApiFetcherTaskExecutorBeanConfig {
    @Bean("githubApiFetcherTaskExecutor")
    public TaskExecutor taskExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(5);
        executor.setQueueCapacity(20);
        executor.setMaxPoolSize(10);
        executor.setThreadNamePrefix("GHF-exec");
        return executor;
    }
}
