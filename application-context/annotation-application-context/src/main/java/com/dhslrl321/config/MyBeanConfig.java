package com.dhslrl321.config;

import com.dhslrl321.controller.HelloController;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MyBeanConfig {
    @Bean
    public HelloController helloController() {
        return new HelloController();
    }
}
