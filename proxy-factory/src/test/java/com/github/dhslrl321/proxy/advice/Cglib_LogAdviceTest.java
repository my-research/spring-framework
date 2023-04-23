package com.github.dhslrl321.proxy.advice;

import com.github.dhslrl321.proxy.LogAdvice;
import com.github.dhslrl321.proxy.fixture.B;
import com.github.dhslrl321.proxy.fixture.IB;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.aop.framework.ProxyFactory;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.aop.support.AopUtils.*;

public class Cglib_LogAdviceTest {

    @Test
    @DisplayName("인터페이스를 구현한 클래스를 proxy 화")
    void interface_proxy() {
        IB b = new B();
        ProxyFactory proxyFactory = new ProxyFactory(b);
        proxyFactory.addAdvice(new LogAdvice());

        IB actual = (IB) proxyFactory.getProxy();
        actual.doB();

        assertThat(isAopProxy(actual)).isTrue();
        assertThat(isJdkDynamicProxy(actual)).isTrue();
        assertThat(isCglibProxy(actual)).isFalse();
    }
}
