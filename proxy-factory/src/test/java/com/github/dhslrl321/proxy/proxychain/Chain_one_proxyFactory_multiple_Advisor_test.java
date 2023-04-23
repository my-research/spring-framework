package com.github.dhslrl321.proxy.proxychain;

import com.github.dhslrl321.proxy.fixture.B;
import com.github.dhslrl321.proxy.fixture.IB;
import com.github.dhslrl321.proxy.proxychain.advice.Advice1;
import com.github.dhslrl321.proxy.proxychain.advice.Advice2;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.aop.Pointcut;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.DefaultPointcutAdvisor;

public class Chain_one_proxyFactory_multiple_Advisor_test {
    @Test
    @DisplayName("하나의 proxyFactory 에 여러 Advisor 추가")
    void name() {
        ProxyFactory proxyFactory = new ProxyFactory(new B());

        proxyFactory.addAdvisor(new DefaultPointcutAdvisor(Pointcut.TRUE, new Advice2()));
        proxyFactory.addAdvisor(new DefaultPointcutAdvisor(Pointcut.TRUE, new Advice1()));

        IB proxy = (IB) proxyFactory.getProxy();
        proxy.doB();
    }
}
