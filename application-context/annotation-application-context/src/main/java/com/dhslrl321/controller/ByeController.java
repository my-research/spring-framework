package com.dhslrl321.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import java.io.IOException;

@Slf4j
@Component
public class ByeController {
    public ByeController() {
        log.info("[!!] hello controller created!");
    }

    public String bye(String name) throws IOException {
        return name + ", hello ! farewell to spring";
    }
}
