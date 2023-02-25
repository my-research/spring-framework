package com.github.dhslrl321.service.profile;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

@Service
@Primary
public class PrimaryGreetingService implements GreetingService {
    @Override
    public String greet() {
        return "iam primaryGreetingService";
    }
}
