package com.github.dhslrl321.app.person;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyController {

    public MyController(@Qualifier("girl") Person person) {
        this.person = person;
    }

    private final Person person;

    @GetMapping("/")
    public ResponseEntity<String> a() {
        person.say();
        return ResponseEntity.ok(person.getClass().toGenericString());
    }
}
