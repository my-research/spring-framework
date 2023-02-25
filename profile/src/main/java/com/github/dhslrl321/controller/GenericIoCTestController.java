package com.github.dhslrl321.controller;

import com.github.dhslrl321.service.qualifier.HelloService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GenericIoCTestController {

    private final HelloService<Integer> service;

    public GenericIoCTestController(HelloService<Integer> service) {
        this.service = service;
    }

    @GetMapping("/generic-test")
    public String hello() {
        return service.hello(10);
    }
}
