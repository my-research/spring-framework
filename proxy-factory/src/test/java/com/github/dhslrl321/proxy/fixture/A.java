package com.github.dhslrl321.proxy.fixture;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class A {
    public void doA() {
        log.info("구체 클래스 A 호출됨");
    }

    public void doA2() {
        log.info("PointCut 이 얘는 호출하지 않을 것임");
    }
}

