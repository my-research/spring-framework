package com.github.dhslrl321.aop;

import com.github.dhslrl321.aop.aspect.LogAspectV1;
import com.github.dhslrl321.aop.aspect.LogAspectV2;
import com.github.dhslrl321.aop.core.OrderRepository;
import com.github.dhslrl321.aop.core.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.aop.support.AopUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@Slf4j
//@Import(LogAspectV1.class)
@Import(LogAspectV2.class)
@SpringBootTest
public class AopTest {
    @Autowired
    OrderService orderService;
    @Autowired
    OrderRepository orderRepository;

    @Test
    void aopInfo() {
        assertThat(AopUtils.isAopProxy(orderService)).isTrue();
        assertThat(AopUtils.isAopProxy(orderRepository)).isTrue();
    }

    @Test
    void success() {
        orderService.orderItem("itemA");
    }

    @Test
    void exception() {
        assertThatThrownBy(() -> orderService.orderItem("ex"))
                .isInstanceOf(IllegalStateException.class);
    }
}


