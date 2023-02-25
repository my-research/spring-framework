package com.dhslrl321.tx.a2;

import com.dhslrl321.tx.a1.SecondEntity;
import com.dhslrl321.tx.a1.SecondRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

@Service
@RequiredArgsConstructor
@Slf4j
public class OuterService {
    private final InnerService innerService;
    private final SecondRepository repository;

    @Transactional
    public void saveOnNormal() {

        try {
            innerService.innerMethodThrow();
        } catch (RuntimeException e) {
            log.warn("catch");
        }
        TransactionAspectSupport.currentTransactionStatus().isRollbackOnly();
        repository.save(new SecondEntity("B"));
    }
}
