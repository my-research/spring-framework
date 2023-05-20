package com.github.dhslrl321;

import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Fixtures {
    public final static String URL = "jdbc:h2:mem:test";
    public final static String USERNAME = "sa";
    public final static String PASSWORD = "";
    public static final String SQL = "DROP TABLE IF EXISTS MEMBER CASCADE; " +
            "CREATE TABLE MEMBER (id varchar(15) , balance bigint, primary key (id));";


    public static DataSource dataSource() {
        return new DriverManagerDataSource(Fixtures.URL, Fixtures.USERNAME, Fixtures.PASSWORD);
    }
}
