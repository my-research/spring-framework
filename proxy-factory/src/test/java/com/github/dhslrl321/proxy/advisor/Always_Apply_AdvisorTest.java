package com.github.dhslrl321.proxy.advisor;

import com.github.dhslrl321.proxy.fixture.LogAdvice;
import com.github.dhslrl321.proxy.fixture.A;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.aop.Pointcut;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.DefaultPointcutAdvisor;

class Always_Apply_AdvisorTest {

    DefaultPointcutAdvisor sut = new DefaultPointcutAdvisor(Pointcut.TRUE, new LogAdvice());

    A a = new A();
    ProxyFactory proxyFactory = new ProxyFactory(a);

    @Test
    @DisplayName("항상 proxy 를 적용한다")
    void name() {
        proxyFactory.addAdvisor(sut);
        A proxy = (A) proxyFactory.getProxy();

        proxy.doA();
        proxy.doA2();
    }
}