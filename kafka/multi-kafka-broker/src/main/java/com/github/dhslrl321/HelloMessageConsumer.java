package com.github.dhslrl321;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@EnableKafka
public class HelloMessageConsumer {

    @KafkaListener(topics = "my-test", groupId = "consumer-local-1")
    public void consume(String message) {
        log.info("[KAFKA] consumer message consume [{}]", message);
        System.out.println(message);
    }
}
