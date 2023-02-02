package com.github.dhslrl321;

import com.github.dhslrl321.app.DummyService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class MainApplicationTest {

    @Autowired
    DummyService sut;

    @Test
    void name() {
        sut.order();
    }
}