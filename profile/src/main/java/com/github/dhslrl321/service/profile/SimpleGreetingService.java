package com.github.dhslrl321.service.profile;

import org.springframework.stereotype.Service;

@Service
public class SimpleGreetingService implements GreetingService {
    @Override
    public String greet() {
        return "iam simpleGreetingService";
    }
}
