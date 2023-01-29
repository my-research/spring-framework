package com.github.dhslrl321.transaction.service;

import com.github.dhslrl321.transaction.entity.User;
import com.github.dhslrl321.transaction.repository.UserJpaRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserJoinService {

    private final UserJpaRepository repository;
    private final AccessHistoryService accessHistoryService;

    @Transactional
    public void join(String username) {
        accessHistoryService.logHistory(username);

        User saved = repository.save(User.create(username));
        log.info(saved.toString());

        throw new RuntimeException("강제 롤백 발생");
    }
}
