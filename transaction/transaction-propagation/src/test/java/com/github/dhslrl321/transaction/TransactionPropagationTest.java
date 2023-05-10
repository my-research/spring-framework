package com.github.dhslrl321.transaction;

import com.github.dhslrl321.transaction.entity.AccessHistory;
import com.github.dhslrl321.transaction.repository.AccessHistoryRepository;
import com.github.dhslrl321.transaction.repository.UserJpaRepository;
import com.github.dhslrl321.transaction.service.AccessHistoryService;
import com.github.dhslrl321.transaction.service.UserJoinService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@DataJpaTest
public class TransactionPropagationTest {

    @Autowired
    private UserJoinService sut;

    @Autowired
    private AccessHistoryService accessHistoryService;

    @Autowired
    private UserJpaRepository userRepository;

    @Autowired
    private AccessHistoryRepository repository;

    @Test
    void name() {
        sut.join("jang");
    }
}
