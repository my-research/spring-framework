package com.github.dhslrl321.message;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class HelloMessageConsumer {

    @KafkaListener(topics = "my-test", groupId = "for-test")
    public void consume(String message) {
        log.info("[KAFKA] consumer message consume [{}]", message);
    }
}
