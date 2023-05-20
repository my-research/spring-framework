package com.github.dhslrl321.member;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.datasource.DataSourceUtils;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static java.util.Objects.nonNull;

@RequiredArgsConstructor
public class MemberRepository {

    private final DataSource dataSource;

    public Member findBy(long id) {
        Connection conn = connection();
        PreparedStatement statement = null;
        ResultSet rs = null;

        Member found = null;

        try {
            statement = conn.prepareStatement("SELECT * FROM members WHERE id = ?");
            statement.setLong(1, id);
            rs = statement.executeQuery();
            rs.next();
            found = Member.instantiate(rs.getLong(1), rs.getString(2), rs.getLong(3));
        } catch (SQLException e) {
            throw new IllegalArgumentException("존재하지 않는 유저");
        }
        release(conn, statement, rs);
        return found;
    }

    public void save(Member member) {
        Connection conn = connection();
        PreparedStatement statement = null;
        ResultSet rs = null;

        try {
            throwWhenExist(member.getId(), conn, statement, rs);

            statement = conn.prepareStatement("INSERT INTO members VALUES (?, ?, ?)");
            statement.setLong(1, member.getId());
            statement.setString(2, member.getName());
            statement.setLong(3, member.getBalance());

            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        release(conn, statement, rs);
    }

    private static void throwWhenExist(long id, Connection conn, PreparedStatement statement, ResultSet rs) throws SQLException {
        statement = conn.prepareStatement("SELECT count(1) FROM members WHERE id = ?");
        statement.setLong(1, id);
        rs = statement.executeQuery();
        rs.next();
        if (rs.getInt(1) > 0) {
            throw new IllegalStateException("이미 존재하는 유저");
        }
    }

    public void update(Member member) {

        Connection conn = connection();
        PreparedStatement statement = null;

        try {
            statement = conn.prepareStatement("UPDATE members SET balance = ? WHERE id = ?");
            statement.setLong(1, member.getBalance());
            statement.setLong(2, member.getId());

            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        release(conn, statement, null);
    }

    private Connection connection() {
        return DataSourceUtils.getConnection(dataSource);
    }

    private void release(Connection connection, PreparedStatement statement, ResultSet rs) {

        if (nonNull(rs)) {
            try {
                rs.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

        release(connection, statement);
    }

    private void release(Connection connection, PreparedStatement statement) {
        try {
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("statement close 실패");
        }
        DataSourceUtils.releaseConnection(connection, dataSource);
    }
}
