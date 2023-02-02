package com.github.dhslrl321.playground;

import com.likelen.identifier.core.LongValueGenerator;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class SomeServiceTest {
    @Autowired
    LongValueGenerator sut;

    @Test
    void name() {
        long gen = sut.gen();
        System.out.println("gen = " + gen);
    }
}