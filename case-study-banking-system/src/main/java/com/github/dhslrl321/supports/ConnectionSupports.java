package com.github.dhslrl321.supports;

import org.springframework.jdbc.datasource.DataSourceUtils;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static java.util.Objects.nonNull;

/**
 * 다음 기능을 제공하는 abstraction
 * - check duplication
 * - ensure presence
 * - release db resource
 */
public class ConnectionSupports {

    final DataSource dataSource;

    public ConnectionSupports(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public boolean isExist(String tableName, long id) throws SQLException {
        Connection conn = connection();

        PreparedStatement statement = conn.prepareStatement("SELECT count(1) FROM " + tableName + " WHERE id = ?");
        statement.setLong(1, id);
        ResultSet rs = statement.executeQuery();
        rs.next();

        boolean isExist = rs.getInt(1) > 0;
        release(conn, statement);
        return isExist;
    }

    public Connection connection() {
        return DataSourceUtils.getConnection(dataSource);
    }

    public ResultSet executeQuery(PreparedStatement statement) throws SQLException {
        ResultSet resultSet = statement.executeQuery();
        resultSet.next();
        return resultSet;
    }

    public void release(Connection connection, PreparedStatement statement, ResultSet rs) {

        if (nonNull(rs)) {
            try {
                rs.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

        release(connection, statement);
    }

    public void release(Connection connection, PreparedStatement statement) {
        if (nonNull(statement)) {
            try {
                statement.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        DataSourceUtils.releaseConnection(connection, dataSource);
    }
}
