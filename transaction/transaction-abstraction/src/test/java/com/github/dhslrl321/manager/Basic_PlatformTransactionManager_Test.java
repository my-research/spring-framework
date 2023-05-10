package com.github.dhslrl321.manager;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.transaction.PlatformTransactionManager;

import static com.github.dhslrl321.Fixtures.dataSource;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

/**
 * PlatformTransactionManager
 *
 * central interface of imperative transaction infrastructure
 *
 * it provides three essential method
 *
 * - getTransaction()
 *  - return currently active transaction or create new one
 * - commit()
 * - rollback()
 */
public class Basic_PlatformTransactionManager_Test {
    PlatformTransactionManager sut;

    @Test
    @DisplayName("DataSource 가 생성자로 꼭 필요하다")
    void name() {

        sut = new DataSourceTransactionManager();

        assertThatThrownBy(() -> sut.getTransaction(null))
                .isInstanceOf(IllegalStateException.class).hasMessage("No DataSource set");
    }

    @Test
    @DisplayName("DataSource 가 생성자로 꼭 필요하다")
    void name2() {
        sut = new DataSourceTransactionManager(dataSource());

        assertDoesNotThrow(() -> sut.getTransaction(null));
    }
}
