package com.github.dhslrl321.controller;

import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Profile("dev")
@Primary
@RestController
public class DevController {
    @GetMapping("/")
    public String hello() {
        return "hello dev";
    }
}
