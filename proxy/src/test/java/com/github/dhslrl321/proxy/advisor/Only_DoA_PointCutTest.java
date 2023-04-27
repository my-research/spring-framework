package com.github.dhslrl321.proxy.advisor;

import com.github.dhslrl321.proxy.advisor.pointcut.DoAPointCut;
import com.github.dhslrl321.proxy.fixture.LogAdvice;
import com.github.dhslrl321.proxy.fixture.A;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.DefaultPointcutAdvisor;

public class Only_DoA_PointCutTest {

    A a = new A();
    ProxyFactory proxyFactory = new ProxyFactory(a);
    DefaultPointcutAdvisor advisor = new DefaultPointcutAdvisor(new DoAPointCut(), new LogAdvice());

    @Test
    @DisplayName("pointcut 을 이용해서 특정 메서드만 proxy 가 적용되도록 한다")
    void name() {
        proxyFactory.addAdvisor(advisor);

        A proxy = (A) proxyFactory.getProxy();

        proxy.doA();
        proxy.doA2();
    }
}
