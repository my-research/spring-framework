package com.github.dhslrl321.proxy.fixture;

import lombok.extern.slf4j.Slf4j;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
public class B implements IB {

    @Override
    public void doB() {
        log.info("IB 인터페이스를 구현한 B 실행됨");
    }
}
