package com.github.dhslrl321;

import java.sql.*;
import java.util.Objects;

public class ConnectionSupports {

    private final static String URL = "jdbc:h2:mem:test";
    private final static String USERNAME = "sa";
    private final static String PASSWORD = "";

    public static Connection connectionWith(String ddl) {
        PreparedStatement init = null;
        try {
            Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            init = connection.prepareStatement(ddl);
            init.executeUpdate();
            return connection;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("DDL init 실패");
        } finally {
            try {
                assert init != null;
                init.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void close(Connection connection, PreparedStatement stmt, ResultSet resultSet) {
        try {
            if (Objects.nonNull(resultSet)) {
                resultSet.close();
            }
            close(connection, stmt);
        } catch (SQLException e) {
            throw new RuntimeException("[!!] Close Failed");
        }
    }

    public static void close(Connection connection, PreparedStatement stmt) {
        try {

            if (Objects.nonNull(stmt)) {
                stmt.close();
            }

            if (Objects.nonNull(connection)) {
                connection.close();
            }
        } catch (SQLException e) {
            throw new RuntimeException("[!!] Close Failed");
        }
    }
}
