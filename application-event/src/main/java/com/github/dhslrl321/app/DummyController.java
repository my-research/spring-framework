package com.github.dhslrl321.app;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/apis")
public class DummyController {
    @GetMapping("/hello")
    public ResponseEntity<TC> doTest() {
        return ResponseEntity.ok(TC.of(true));
    }
}
