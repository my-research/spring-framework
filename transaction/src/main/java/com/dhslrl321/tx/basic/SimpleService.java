package com.dhslrl321.tx.basic;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SimpleService {

    private final FirstEntityRepository firstRepository;
    private final SecondEntityRepository secondRepository;

    public List<FirstEntity> findAll() {
        return firstRepository.findAll();
    }

    public void add(FirstEntity entity) {
        firstRepository.save(entity);
    }

    public void addThenThrows2(FirstEntity entity) {
        firstRepository.save(entity);
    }

    public void first() {
        second(); // rollback
        firstRepository.save(FirstEntity.newInstance("A")); // no
        throw new RuntimeException("예외 발생!");
    }

    @Transactional
    public void second() {
        secondRepository.save(new SecondEntity("Hello"));
    }
}
