package com.github.dhslrl321;

import com.github.dhslrl321.poc.AnyService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;

import javax.annotation.PostConstruct;

@RequiredArgsConstructor
@SpringBootApplication
@EnableAsync
public class AsyncApplication {

    private final AnyService anyService;

    public static void main(String[] args) {
        SpringApplication.run(AsyncApplication.class, args);
    }

    @PostConstruct
    public void init() throws InterruptedException {
        anyService.doAny();
    }
}
