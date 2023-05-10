package com.github.dhslrl321.transaction.service;

import com.github.dhslrl321.transaction.entity.AccessHistory;
import com.github.dhslrl321.transaction.repository.AccessHistoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AccessHistoryService {

    private final AccessHistoryRepository repository;

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void logHistory(String username) {
        repository.save(AccessHistory.by(username));
    }
}
