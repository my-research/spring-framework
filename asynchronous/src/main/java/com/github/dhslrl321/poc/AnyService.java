package com.github.dhslrl321.poc;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class AnyService {
    @Async("anyTaskExecutor")
    public void doAny() throws InterruptedException {
        log.info("task 시작됨");
        Thread.sleep(10000);
    }
}
