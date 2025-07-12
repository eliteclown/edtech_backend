package com.cybercity.application.configs;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;

@Configuration
@EnableAsync
public class AppConfig {

    @Bean(name = "emailExecutor")
    public Executor emailExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(10); // active threads
        executor.setMaxPoolSize(30);  // max if load spikes
        executor.setQueueCapacity(1000); // max pending emails
        executor.setThreadNamePrefix("Email-");
        executor.initialize();
        return executor;
    }
}
