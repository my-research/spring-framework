package com.github.dhslrl321;

import com.github.dhslrl321.message.HelloMessageProducer;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MyController {

    private final HelloMessageProducer producer;

    @GetMapping("/hello")
    public ResponseEntity<String> hello() {

        producer.sendMessage("hello world~");

        return ResponseEntity.ok("success");
    }
}
