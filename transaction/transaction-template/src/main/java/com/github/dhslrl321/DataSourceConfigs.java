package com.github.dhslrl321;

import com.zaxxer.hikari.HikariDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class DataSourceConfigs {

    public static DataSource hikariConnectionPoolDataSource4Test() {
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
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return dataSource;
    }

    public static DataSource hikariConnectionPoolDataSource() {
        HikariDataSource dataSource = new HikariDataSource();

        dataSource.setJdbcUrl("jdbc:h2:mem:test");
        dataSource.setUsername("sa");
        dataSource.setPassword("");
        dataSource.setMaximumPoolSize(10);

        return dataSource;
    }
}
