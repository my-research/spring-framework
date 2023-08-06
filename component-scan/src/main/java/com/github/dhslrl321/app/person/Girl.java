package com.github.dhslrl321.app.person;

import org.springframework.stereotype.Component;

@Component("girl")
public class Girl implements Person {
    @Override
    public void say() {
        System.out.println("false = " + false);
    }
}
