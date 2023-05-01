package com.dhslrl321.tx.a1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@SpringBootTest
public class SimpleServiceTest {
    @Autowired
    SimpleService sut;

    @Autowired
    FirstRepository firstRepository;

    @Autowired
    SecondRepository secondRepository;

    @Test
    void name() {
        List<FirstEntity> actual = sut.findAll();

        assertThat(actual.size()).isEqualTo(0);
    }

    @Test
    @DisplayName("선언적 트랜잭션 사용하지 않았을 때, 예외가 발생해도 롤백되지 않음")
    void name2() {
        assertThatThrownBy(() -> sut.addThenThrows2(FirstEntity.newInstance(UUID.randomUUID().toString())));

        assertThat(sut.findAll().size()).isEqualTo(1);
    }

    @Test
    @DisplayName("롤백::proxy 를 사용하므로 내부 메서드 호출은 동작하지 않음")
    void name3() {
        assertThatThrownBy(() -> sut.first());

        assertThat(sut.findAll().size()).isEqualTo(1);
    }

    @Test
    void name4() {
        assertThatThrownBy(() -> sut.first());

        assertThat(firstRepository.findAll().size()).isEqualTo(1);
        assertThat(secondRepository.findAll().size()).isEqualTo(1);
    }
}
