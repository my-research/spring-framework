package com.github.dhslrl321;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.test.EmbeddedKafkaBroker;
import org.springframework.kafka.test.utils.KafkaTestUtils;

@SpringBootTest
public class KafkaConsumerTest {
    @Autowired
    private EmbeddedKafkaBroker embeddedKafkaBroker;

    @Autowired
    private SimpleKafkaListener simpleKafkaListener;

    @Test
    public void testKafkaConsumer() throws InterruptedException {
        // Kafka로 메시지를 보냅니다.
        String testTopic = "test-topic";
        String message = "Hello, Kafka!";

        kafkaTemplate.send(testTopic, message);


        // SimpleKafkaListener의 payload가 업데이트될 때까지 대기합니다.
        simpleKafkaListener.getLatch().await(10, TimeUnit.SECONDS);

        // 메시지가 성공적으로 수신되었는지 확인합니다.
        assertEquals(message, simpleKafkaListener.getPayload());
    }
}
