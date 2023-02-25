package com.github.dhslrl321.service.qualifier;

import org.springframework.stereotype.Service;

@Service
public class StringHelloService implements HelloService<String>{
    @Override
    public String hello(String s) {
        return s + "입니다";
    }
}
