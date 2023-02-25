package com.dhslrl321.tx.a2;

import com.dhslrl321.tx.a1.FirstEntity;
import com.dhslrl321.tx.a1.FirstRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class InnerService {
    private final FirstRepository repository;

    @Transactional
    public void innerMethodThrow() {
        repository.save(FirstEntity.newInstance("A"));
        throw new RuntimeException();
    }
}
