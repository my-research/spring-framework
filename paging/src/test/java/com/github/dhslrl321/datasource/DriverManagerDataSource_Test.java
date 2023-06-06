package com.github.dhslrl321.datasource;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * DriverManagerDataSource.class
 *
 * configuring the plain old JDBC DriverManager via bean properties,
 * and returning a new Connection from every getConnection call
 */
public class DriverManagerDataSource_Test {

    private final static String URL = "jdbc:h2:mem:test";
    private final static String USERNAME = "sa";
    private final static String PASSWORD = "";

    DataSource sut;

    @Test
    @DisplayName("old 스타일의 DriverManager 를 통한 Connection 획득")
    void name() throws SQLException {
        sut = new DriverManagerDataSource(URL, USERNAME, PASSWORD);

        Connection actual = sut.getConnection(); // connection 을 획득하기 위해서 설정정보가 필요하지 않음

        assertThat(actual.isClosed()).isFalse();
    }
}
