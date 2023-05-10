package com.github.dhslrl321.manager;

import com.github.dhslrl321.Fixtures;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * TransactionStatus
 *  - status of current transaction's status
 * getTransaction()
 */
public class TransactionStatus_test {
    PlatformTransactionManager txManager = new DataSourceTransactionManager(Fixtures.dataSource());;

    @Test
    void name() {
        TransactionStatus sut = txManager.getTransaction(new DefaultTransactionDefinition());

        assertThat(sut.isNewTransaction()).isTrue();
    }
}
