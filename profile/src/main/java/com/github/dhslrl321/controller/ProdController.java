package com.github.dhslrl321.controller;

import com.github.dhslrl321.service.GreetingService;
import org.springframework.context.annotation.Profile;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Profile("prod")
@RestController
public class ProdController {

    private final GreetingService service;

    public ProdController(GreetingService service) {
        this.service = service;
    }

    @GetMapping("/")
    public String hello() {
        return "hello prod, " + service.greet();
    }
}
