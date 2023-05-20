package com.github.dhslrl321.supports;

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
            connection.prepareStatement("DROP TABLE IF EXISTS members CASCADE; " +
                            "CREATE TABLE members (id bigint, name varchar(25), balance bigint, primary key (id));")
                    .execute();

            connection.prepareStatement("DROP TABLE IF EXISTS transfer_audits CASCADE; " +
                            "CREATE TABLE transfer_audits (id BIGINT, from_id BIGINT, to_id BIGINT, amount BIGINT, created_at DATETIME, primary key (id));")
                    .execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return dataSource;
    }
}
