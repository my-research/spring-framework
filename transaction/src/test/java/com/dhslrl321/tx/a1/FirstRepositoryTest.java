package com.dhslrl321.tx.a1;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;


@DataJpaTest
public class FirstRepositoryTest {
    @Autowired
    FirstRepository sut;

    @Test
    void name() {
        String name = UUID.randomUUID().toString();

        sut.save(FirstEntity.newInstance(name));

        FirstEntity actual = sut.findByName(name);

        assertThat(actual).isNotNull();
    }
}
