package com.github.dhslrl321.domain.account;

import com.github.dhslrl321.supports.DataSourceConfigs;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import javax.sql.DataSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class AccountRepositoryTest {

    AccountRepository sut;

    @BeforeEach
    void setUp() {
        DataSource dataSource = DataSourceConfigs.hikariConnectionPoolDataSource();
        sut = new AccountRepository(dataSource);
    }

    @Test
    @DisplayName("pk 제약조건")
    void name() {
        Account account = Account.instantiate(1, "jang", 100);

        sut.save(account);

        assertThatThrownBy(() -> sut.save(account))
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("이미 존재하는 유저");
    }

    @Test
    @DisplayName("조회")
    void name2() {
        Account account = Account.instantiate(1, "jang", 100);

        sut.save(account);
        Account actual = sut.findBy(1);

        assertThat(actual.getId()).isEqualTo(1);
    }

    @Test
    @DisplayName("존재하지 않는 대상을 조회")
    void name3() {
        assertThatThrownBy(() -> sut.findBy(9999))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("존재하지 않는 유저");
    }

    @Test
    void name4() {
        Account account = Account.instantiate(1, "jang", 1000);
        sut.save(account);

        account.minus(100);
        sut.update(account);

        Account actual = sut.findBy(1);

        assertThat(account).isEqualTo(actual);
    }
}