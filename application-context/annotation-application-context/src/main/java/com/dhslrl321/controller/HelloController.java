package com.dhslrl321.controller;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

@Slf4j
public class HelloController {
    public HelloController() {
        log.info("[!!] hello controller created!");
    }

    public String hello(String name) throws IOException {
        return name + ", hello ! welcome to spring";
    }
}
