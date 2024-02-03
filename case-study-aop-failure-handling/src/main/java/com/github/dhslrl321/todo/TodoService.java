package com.github.dhslrl321.todo;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Slf4j
@Service
@RequiredArgsConstructor
public class TodoService {

    private final TodoRepository repository;

    @Transactional
    public Todo create(CreateTodoRequest request) {
        log.info("todo 생성 요청됨 {}", request);
        Todo saved = repository.save(Todo.builder()
                .userId(request.getUserId())
                .name(request.getName())
                .createdAt(LocalDateTime.now())
                .build());
        throw new IllegalArgumentException("ak");
        /*log.info("todo 생성 저장됨 {}", request);
        return saved;*/
    }
}
