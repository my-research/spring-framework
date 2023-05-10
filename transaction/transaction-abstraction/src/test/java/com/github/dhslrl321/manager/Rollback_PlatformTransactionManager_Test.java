package com.github.dhslrl321.manager;

import com.github.dhslrl321.Fixtures;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import static org.assertj.core.api.Assertions.assertThat;

public class Rollback_PlatformTransactionManager_Test {
    PlatformTransactionManager sut;
    DataSource dataSource = Fixtures.dataSource();

    @BeforeEach
    void setUp() throws SQLException {

        sut = new DataSourceTransactionManager(dataSource);

        dataSource.getConnection().prepareStatement(Fixtures.SQL).execute();
        dataSource.getConnection().prepareStatement("INSERT INTO member VALUES ('userA', 1000)").execute();
    }

    @Test
    @DisplayName("TransactionManager 로 tx 를 관리")
    void name() throws SQLException {
        TransactionStatus tx = sut.getTransaction(new DefaultTransactionDefinition());

        getConnection().prepareStatement("UPDATE member SET balance = 5000 WHERE id = 'userA'").execute();

        sut.rollback(tx);

        Connection c2 = getConnection();
        ResultSet rs = c2.prepareStatement("SELECT * FROM member WHERE id = 'userA'").executeQuery(); rs.next();

        assertThat(rs.getInt(2)).isEqualTo(1000);
    }

    @Test
    @DisplayName("TransactionSynchronizationManager 가 제공하는 커넥션이 아니면 동작하지 않는다" +
            "이유: TransactionManager 가  ")
    void non_transaction_synchronization_manager() throws Exception {
        TransactionStatus tx = sut.getTransaction(new DefaultTransactionDefinition());

        dataSource.getConnection().prepareStatement("UPDATE member SET balance = 5000 WHERE id = 'userA'").execute();

        sut.rollback(tx);

        Connection c2 = getConnection();
        ResultSet rs = c2.prepareStatement("SELECT * FROM member WHERE id = 'userA'").executeQuery(); rs.next();

        assertThat(rs.getInt(2)).isEqualTo(1000);
    }

    @Test
    @DisplayName("transactionManager 없이 tx 를 직접 관리")
    void non_tx() throws SQLException {
        Connection c1 = getConnection();
        c1.prepareStatement("BEGIN TRANSACTION").execute();
        c1.prepareStatement("UPDATE member SET balance = 5000 WHERE id = 'userA'").execute();
        c1.prepareStatement("ROLLBACK").execute();

        Connection c2 = getConnection();
        ResultSet rs = c2.prepareStatement("SELECT * FROM member WHERE id = 'userA'").executeQuery(); rs.next();

        assertThat(rs.getInt(2)).isEqualTo(1000);
    }

    private Connection getConnection() {
        return DataSourceUtils.getConnection(dataSource);
    }
}
