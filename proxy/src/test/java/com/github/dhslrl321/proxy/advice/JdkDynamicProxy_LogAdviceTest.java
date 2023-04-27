package com.github.dhslrl321.proxy.advice;

import com.github.dhslrl321.proxy.fixture.LogAdvice;
import com.github.dhslrl321.proxy.fixture.A;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.aop.framework.ProxyFactory;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.aop.support.AopUtils.*;

class JdkDynamicProxy_LogAdviceTest {

    @Test
    @DisplayName("구체 클래스를 proxy 화")
    void concrete_proxy() {
        A a = new A();
        ProxyFactory proxyFactory = new ProxyFactory(a);
        proxyFactory.addAdvice(new LogAdvice());

        A proxy = (A) proxyFactory.getProxy();
        proxy.doA();

        assertThat(isAopProxy(proxy)).isTrue();
        assertThat(isCglibProxy(proxy)).isTrue();
        assertThat(isJdkDynamicProxy(proxy)).isFalse();
    }
}