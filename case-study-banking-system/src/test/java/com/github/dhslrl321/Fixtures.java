package com.github.dhslrl321;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.support.JdbcTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

public class Fixtures {
    private final static String URL = "jdbc:h2:mem:test;LOCK_TIMEOUT=5000";
    private final static String USERNAME = "sa";
    private final static String PASSWORD = "";

    public static DataSource dataSource() {
        HikariDataSource dataSource = new HikariDataSource();
        dataSource.setJdbcUrl(URL);
        dataSource.setUsername(USERNAME);
        dataSource.setPassword(PASSWORD);
        dataSource.setMaximumPoolSize(10);
        return dataSource;
    }

    public static PlatformTransactionManager transactionManager() {
        return new DataSourceTransactionManager(dataSource());
    }
}
