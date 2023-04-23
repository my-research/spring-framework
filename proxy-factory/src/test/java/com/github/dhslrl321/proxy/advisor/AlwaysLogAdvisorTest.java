package com.github.dhslrl321.proxy.advisor;

import com.github.dhslrl321.proxy.LogAdvice;
import com.github.dhslrl321.proxy.fixture.A;
import org.junit.jupiter.api.Test;
import org.springframework.aop.Pointcut;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.DefaultPointcutAdvisor;

import static org.junit.jupiter.api.Assertions.*;

class AlwaysLogAdvisorTest {

    DefaultPointcutAdvisor sut = new DefaultPointcutAdvisor(Pointcut.TRUE, new LogAdvice());

    A a = new A();
    ProxyFactory proxyFactory = new ProxyFactory(a);

    @Test
    void name() {
        proxyFactory.addAdvisor(sut);
    }
}