package com.dhslrl321.tx;

import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Connections {

    private final static String URL = "jdbc:h2:mem:test;LOCK_TIMEOUT=5000";
    private final static String USERNAME = "sa";
    private final static String PASSWORD = "";

    static HikariDataSource dataSource = new HikariDataSource();

    static {
        dataSource.setJdbcUrl(URL);
        dataSource.setUsername(USERNAME);
        dataSource.setPassword(PASSWORD);
        dataSource.setMaximumPoolSize(10);
    }

    public static Connection newOne() throws SQLException {
        return dataSource.getConnection();
    }

    public static void init(String sql) {
        try (Connection connection = dataSource.getConnection()) {
            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Connections init 실패");
        }
    }

    public static void initMemberTable() {
        init("DROP TABLE IF EXISTS MEMBER CASCADE; " +
                "CREATE TABLE MEMBER (id varchar(15) , balance bigint, primary key (id));");
    }
}
