package com.github.dhslrl321.proxy;

import org.aopalliance.aop.Advice;
import org.springframework.aop.Advisor;

public class AlwaysLogAdvisor implements Advisor {
    @Override
    public Advice getAdvice() {
        return null;
    }

    @Override
    public boolean isPerInstance() {
        return false;
    }
}
