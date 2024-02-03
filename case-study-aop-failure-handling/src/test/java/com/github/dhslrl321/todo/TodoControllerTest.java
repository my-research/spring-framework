package com.github.dhslrl321.todo;

import com.github.dhslrl321.spring.aop.FailureLoggingAspect;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;

@Import(FailureLoggingAspect.class)
@SpringBootTest
class TodoControllerTest {

    @Autowired
    TodoController sut;

    @Autowired
    MockMvc mockMvc;

    @Test
    void name() {
        
    }
}
