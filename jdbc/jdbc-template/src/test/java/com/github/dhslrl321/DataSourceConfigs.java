package com.github.dhslrl321;

import com.zaxxer.hikari.HikariDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class DataSourceConfigs {
    public static DataSource hikariConnectionPoolDataSource() {
        HikariDataSource dataSource = new HikariDataSource();

        dataSource.setJdbcUrl("jdbc:h2:mem:test");
        dataSource.setUsername("sa");
        dataSource.setPassword("");
        dataSource.setMaximumPoolSize(10);

        try {
            Connection connection = dataSource.getConnection();
            connection.prepareStatement("DROP TABLE IF EXISTS accounts CASCADE; " +
                            "CREATE TABLE accounts (id bigint, name varchar(25), balance bigint, primary key (id));")
                    .execute();

            connection.prepareStatement("DROP TABLE IF EXISTS transfer_audits CASCADE; " +
                            "CREATE TABLE transfer_audits (id BIGINT, from_id BIGINT, to_id BIGINT, amount BIGINT, created_at DATETIME, primary key (id));")
                    .execute();

            connection.prepareStatement("DROP TABLE IF EXISTS players CASCADE; " +
                            "CREATE TABLE players (id VARCHAR(100),  team_id VARCHAR(100), name varchar(5), primary key (id));")
                    .execute();

            connection.prepareStatement("DROP TABLE IF EXISTS teams CASCADE; " +
                            "CREATE TABLE teams (id VARCHAR(100), name VARCHAR(5), primary key (id));")
                    .execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return dataSource;
    }
}
