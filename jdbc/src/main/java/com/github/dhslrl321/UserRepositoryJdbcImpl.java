package com.github.dhslrl321;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UserRepositoryJdbcImpl {
    public void save(User user) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = Connections.getInitializedConnection();
            preparedStatement = connection.prepareStatement("insert into member values (?, ?)");
            preparedStatement.setLong(1, user.getId());
            preparedStatement.setString(2, user.getName());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                assert preparedStatement != null;
                preparedStatement.close();
                connection.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
