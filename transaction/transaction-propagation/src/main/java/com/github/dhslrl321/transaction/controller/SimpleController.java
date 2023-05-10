package com.github.dhslrl321.transaction.controller;

import com.github.dhslrl321.transaction.service.UserJoinService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class SimpleController {

    private final UserJoinService service;

    @GetMapping("/hello")
    public void String() {
        service.join("jangwonik");
    }
}
