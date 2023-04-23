package com.github.dhslrl321.proxy.advisor.pointcut;

import lombok.extern.slf4j.Slf4j;
import org.springframework.aop.MethodMatcher;

import java.lang.reflect.Method;

@Slf4j
public class DoAMethodMatcher implements MethodMatcher {
    @Override
    public boolean matches(Method method, Class<?> targetClass) {
        boolean isDoA = method.getName().equals("doA");
        log.info("pointCut 내부 호출됨");
        return isDoA;
    }

    @Override
    public boolean isRuntime() {
        return false;
    }

    @Override
    public boolean matches(Method method, Class<?> targetClass, Object... args) {
        return false;
    }
}
