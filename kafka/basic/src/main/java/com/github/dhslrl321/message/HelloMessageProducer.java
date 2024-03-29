package com.github.dhslrl321.message;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class HelloMessageProducer {

    private final KafkaTemplate<String, HelloMessage> kafkaTemplate;

    public void sendMessage(String message) {
        log.info("[KAFKA] producer message publish [{}]", message);
        kafkaTemplate.send("my-test", HelloMessage.of(message));
    }

}
