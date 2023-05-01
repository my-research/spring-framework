package com.github.dhslrl321;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Connections {

    private final static String URL = "jdbc:h2:mem:test";
    private final static String USERNAME = "sa";
    private final static String PASSWORD = "";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USERNAME, PASSWORD);
    }

    public static Connection getInitializedConnection() {
        Connection connection = null;
        PreparedStatement initStatement = null;

        try {
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            initStatement = connection.prepareStatement("" +
                    "drop table if exists users CASCADE; " +
                    "create table users " +
                    "(id bigint , name varchar(255)," +
                    "primary key (id));");
            initStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            assert initStatement != null;
            try {
                initStatement.close();
                connection.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return connection;
    }
}
