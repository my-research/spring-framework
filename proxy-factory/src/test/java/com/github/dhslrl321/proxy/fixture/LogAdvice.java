package com.github.dhslrl321.proxy.fixture;

import lombok.extern.slf4j.Slf4j;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

@Slf4j
public class LogAdvice implements MethodInterceptor {
    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        log.info("Advice 실행됨::called [{}]", invocation.getClass());
        Object result = invocation.proceed();
        log.info("Advice 종료됨");

        return result;
    }
}
