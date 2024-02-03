package com.github.dhslrl321.todo;

import com.github.dhslrl321.spring.aop.FailureLogging;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class TodoController {

    private final TodoService service;

    @FailureLogging(key = "userId")
    @PostMapping("/todos")
    public ResponseEntity<Todo> create(@RequestBody CreateTodoRequest request) {
        return ResponseEntity.ok(service.create(request));
    }
}
