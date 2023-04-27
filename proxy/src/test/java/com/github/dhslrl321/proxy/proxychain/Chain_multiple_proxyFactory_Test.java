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

public class Chain_multiple_proxyFactory_Test {

    /**
     * Interface 구현체만 가능하다.
     * 구체클래스를 proxy target 으로 넣으면 안됨. 이유 찾아야함
     */
    @Test
    @DisplayName("두개의 proxyFactory 에 advisor 추가")
    void name() {
        ProxyFactory proxyFactory1 = new ProxyFactory(new B());
        proxyFactory1.addAdvisor(new DefaultPointcutAdvisor(Pointcut.TRUE, new Advice1()));

        ProxyFactory proxyFactory2 = new ProxyFactory(proxyFactory1.getProxy());
        proxyFactory2.addAdvisor(new DefaultPointcutAdvisor(Pointcut.TRUE, new Advice2()));

        IB proxy = (IB) proxyFactory2.getProxy();

        proxy.doB();
    }
}
