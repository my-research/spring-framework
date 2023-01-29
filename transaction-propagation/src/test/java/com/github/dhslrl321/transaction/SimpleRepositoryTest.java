package com.github.dhslrl321.transaction;

import com.github.dhslrl321.transaction.entity.User;
import com.github.dhslrl321.transaction.repository.UserJpaRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

/**
 * repository 에 대한 기능 테스트
 */
@DataJpaTest
class SimpleRepositoryTest {

    @Autowired
    private UserJpaRepository repository;

    @Test
    void repository_생성_테스트() {
        User user = User.create("jangwonik");

        User actual = repository.save(user);

        User user1 = repository.findById(1L).get();
    }
}
