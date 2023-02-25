package com.github.dhslrl321.service.qualifier;

import org.springframework.stereotype.Service;

@Service
public class IntegerHelloService implements HelloService<Integer> {
    @Override
    public String hello(Integer integer) {
        return integer.toString() + "입니다";
    }
}
