package com.github.dhslrl321.proxy.fixture;

import com.github.dhslrl321.proxy.LogAdvice;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.aop.framework.ProxyFactory;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.aop.support.AopUtils.*;

@Slf4j
public class B implements IB {

    @Override
    public void doB() {
        log.info("IB 인터페이스를 구현한 B 실행됨");
    }
}
