package com.github.dhslrl321.app;

import com.github.dhslrl321.events.OrderedEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/apis")
@RequiredArgsConstructor
public class DummyController {

    private final DummyService service;

    @GetMapping("/hello")
    public ResponseEntity<TC> doTest() {
        service.order();
        return ResponseEntity.ok(TC.of(true));
    }
    @GetMapping("/hello2")
    public ResponseEntity<TC> doTest2() {
        service.dummy2();
        return ResponseEntity.ok(TC.of(true));
    }

}
