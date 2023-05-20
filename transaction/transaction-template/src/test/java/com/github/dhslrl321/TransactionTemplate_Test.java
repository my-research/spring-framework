package com.github.dhslrl321;

import com.zaxxer.hikari.HikariDataSource;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.support.JdbcTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

import java.util.function.Consumer;

public class TransactionTemplate_Test {
    TransactionTemplate sut;

    @Test
    void name() {
        sut = new TransactionTemplate(Fixtures.transactionManager());

        sut.execute(new TransactionCallback<Object>() {
            @Override
            public Object doInTransaction(TransactionStatus status) {
                return null;
            }
        });
    }
}
