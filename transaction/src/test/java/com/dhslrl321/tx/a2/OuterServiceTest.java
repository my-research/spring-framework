package com.dhslrl321.tx.a2;

import com.dhslrl321.tx.a1.FirstRepository;
import com.dhslrl321.tx.a1.SecondRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class OuterServiceTest {
    @Autowired
    OuterService sut;

    @Autowired
    FirstRepository firstRepository;

    @Autowired
    SecondRepository secondRepository;

    @Test
    void name() {
        sut.saveOnNormal();

        assertThat(firstRepository.findAll().size()).isEqualTo(0); // inner
        assertThat(secondRepository.findAll().size()).isEqualTo(1); // outer
    }
}