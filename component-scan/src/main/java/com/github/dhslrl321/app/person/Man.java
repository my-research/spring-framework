package com.github.dhslrl321.app.person;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Primary
@Component
public class Man implements Person {
    @Override
    public void say() {
        System.out.println("\"true\" = " + "true");
    }
}
