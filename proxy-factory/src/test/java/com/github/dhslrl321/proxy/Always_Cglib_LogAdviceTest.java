package com.github.dhslrl321.proxy;

import com.github.dhslrl321.proxy.fixture.B;
import com.github.dhslrl321.proxy.fixture.IB;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.aop.framework.ProxyFactory;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.aop.support.AopUtils.isCglibProxy;
import static org.springframework.aop.support.AopUtils.isJdkDynamicProxy;

public class Always_Cglib_LogAdviceTest {

    @Test
    @DisplayName("인터페이스가 존재하든 존재하지 않든, 항상 cglib 로 proxy 생성")
    void always_cglib_proxy() {
        IB b = new B();
        ProxyFactory proxyFactory = new ProxyFactory(b);
        proxyFactory.addAdvice(new LogAdvice());
        IB actual = (IB) proxyFactory.getProxy();
        actual.doB();

        assertThat(isJdkDynamicProxy(actual)).isTrue();
        assertThat(isCglibProxy(actual)).isFalse();

        proxyFactory.setProxyTargetClass(true); // use cglib

        assertThat(isJdkDynamicProxy(actual)).isTrue();
        assertThat(isCglibProxy(actual)).isFalse();
    }
}
