package com.github.dhslrl321;

import com.github.dhslrl321.message.HelloMessageConsumer;
import com.github.dhslrl321.message.HelloMessageProducer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.context.TestPropertySource;
import org.testcontainers.containers.KafkaContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

@SpringBootTest
@TestPropertySource(properties = {"spring.kafka.consumer.auto-offset-reset=earliest"})
@Testcontainers
class HelloMessageProducerTest {

    @Autowired
    HelloMessageProducer sut;

    @Autowired
    HelloMessageConsumer consumer;

    @Container
    static final KafkaContainer kafka = new KafkaContainer(
            DockerImageName.parse("confluentinc/cp-kafka:7.3.3")
    );

    @DynamicPropertySource
    static void overrideProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.kafka.bootstrap.servers", kafka::getBootstrapServers);
    }

    @Test
    void name() {
        sut.sendMessage("asdf");
    }
}
