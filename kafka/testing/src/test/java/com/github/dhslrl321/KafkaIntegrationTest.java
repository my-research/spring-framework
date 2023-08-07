package com.github.dhslrl321;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.test.context.EmbeddedKafka;
import org.springframework.test.annotation.DirtiesContext;

import java.util.concurrent.TimeUnit;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@DirtiesContext
@EmbeddedKafka(partitions = 1, brokerProperties = {"listeners=PLAINTEXT://localhost:9092", "port=9092"})
public class KafkaIntegrationTest {
    @Autowired
    private SimpleKafkaListener consumer;

    @Autowired
    private SimpleKafkaProducer producer;

    @Value("${test.topic}")
    private String topic;

    @Test
    public void test() throws Exception {
        String message = "테스트 완료~";

        producer.send(topic, message);

        boolean messageConsumed = consumer.getLatch().await(10, TimeUnit.SECONDS);

        assertThat(messageConsumed).isTrue();
        assertThat(consumer.getPayload()).isEqualTo(message);
    }
}
