package com.github.dhslrl321.connection;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.DriverManager;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * DriverManager
 *  It provides methods to dynamically load and unload JDBC drivers at runtime,
 *  allowing applications to connect to different databases using different drivers
 *  without requiring recompilation or code changes.
 *
 *   Primary purpose is to establish database connections by providing a unified interface
 *   for driver registration and connection management.
 *
 * Connection
 *
 *  The connection object is responsible for establishing and managing the connection
 *  to the database server.
 *  It allows the application to execute SQL statements, retrieve query results,
 *  and perform various database operations
 *
 *  하는 일
 *  1. Establishing a Connection
 *  2. Executing SQL Statements
 *  3. Transaction Management
 *  4. Retrieving Results
 *  5. Closing the Connection
 */
public class Connection_Test {

    private final static String URL = "jdbc:h2:mem:test";
    private final static String USERNAME = "sa";
    private final static String PASSWORD = "";

    Connection sut;

    @Test
    @DisplayName("DriverManager 를 이용해서 연결할 수 있다")
    void name() throws Exception {
        // driverManager 를 이용하면 라이브러리 패스에 존재하는 데이터소스를 이용한다 (registeredDrivers)
        // 그리고 URL 에 명시된 데이터소스도 함께 사용
        sut = DriverManager.getConnection(URL, USERNAME, PASSWORD);

        assertThat(sut).isNotNull();
        sut.close();
    }
}
