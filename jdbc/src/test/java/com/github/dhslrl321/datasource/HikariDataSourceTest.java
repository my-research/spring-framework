package com.github.dhslrl321.datasource;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import static org.assertj.core.api.Assertions.assertThat;

public class HikariDataSourceTest {
    private final static String URL = "jdbc:h2:mem:test";
    private final static String USERNAME = "sa";
    private final static String PASSWORD = "";
    @Test
    void name() throws InterruptedException, SQLException {
        try (HikariDataSource sut = new HikariDataSource()) {
            sut.setJdbcUrl(URL);
            sut.setUsername(USERNAME);
            sut.setPassword(PASSWORD);
            sut.setMaximumPoolSize(10);
            sut.setPoolName("hello");

            Connection actual = sut.getConnection();

            assertThat(actual).isNotNull();
            assertThat(actual.getClass().getSimpleName()).isEqualTo("HikariProxyConnection");
        }
    }
}
