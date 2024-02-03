package com.github.dhslrl321.spring.aop;

import com.github.dhslrl321.logging.FailureLogRepository;
import com.github.dhslrl321.logging.FailureLoggingService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AopConfig {
    @Bean
    public FailureLoggingAspect failureLoggingAspect(FailureLoggingService service) {
        return new FailureLoggingAspect(service);
    }
}
