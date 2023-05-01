package com.dhslrl321.tx.a1;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SimpleService {

    private final FirstRepository firstRepository;
    private final SecondRepository secondRepository;

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
        second();
        firstRepository.save(FirstEntity.newInstance("A")); // save
        throw new RuntimeException("예외 발생!");
    }

    @Transactional // not working
    public void second() {
        secondRepository.save(new SecondEntity("Hello"));
    }
}
