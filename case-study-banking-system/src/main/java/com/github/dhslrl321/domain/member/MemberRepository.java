package com.github.dhslrl321.domain.member;

import com.github.dhslrl321.domain.audit.TransferAudit;
import com.github.dhslrl321.supports.ConnectionSupports;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Repository
public class MemberRepository {

    public static final String TABLE_NAME = "members";
    private final ConnectionSupports supports;

    public MemberRepository(DataSource dataSource) {
        supports = new ConnectionSupports(dataSource);
    }

    public List<Member> findAll() {

        try {
            Connection conn = supports.connection();

            PreparedStatement statement = conn.prepareStatement("SELECT * FROM members");

            ResultSet rs = statement.executeQuery();
            List<Member> members = new ArrayList<>();
            while (rs.next()) {
                members.add(Member.instantiate(
                        rs.getLong(1),
                        rs.getString(2),
                        rs.getLong(3)));
            }

            supports.release(conn, statement, rs);
            return members;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Member findBy(long id) {
        Connection conn = supports.connection();
        PreparedStatement statement = null;
        try {
            if (!supports.isExist(TABLE_NAME, id)) {
                throw new IllegalArgumentException("존재하지 않는 유저");
            }
            statement = conn.prepareStatement("SELECT * FROM members WHERE id = ?");
            statement.setLong(1, id);

            ResultSet rs = supports.executeQuery(statement);
            Member member = Member.instantiate(rs.getLong(1), rs.getString(2), rs.getLong(3));

            supports.release(conn, statement, rs);
            return member;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            supports.release(conn, statement);
        }
    }

    public void updateAll(Member... members) {
        Arrays.stream(members).forEach(this::update);
    }

    public void save(Member member) {
        Connection conn = supports.connection();
        PreparedStatement statement = null;

        try {
            if (supports.isExist(TABLE_NAME, member.getId())) {
                throw new IllegalStateException("이미 존재하는 유저");
            }

            statement = conn.prepareStatement("INSERT INTO members VALUES (?, ?, ?)");
            statement.setLong(1, member.getId());
            statement.setString(2, member.getName());
            statement.setLong(3, member.getBalance());

            statement.executeUpdate();
            supports.release(conn, statement);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            supports.release(conn, statement);
        }
    }

    public void update(Member member) {

        Connection conn = supports.connection();
        PreparedStatement statement = null;

        try {
            statement = conn.prepareStatement("UPDATE members SET balance = ? WHERE id = ?");
            statement.setLong(1, member.getBalance());
            statement.setLong(2, member.getId());

            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            supports.release(conn, statement);
        }
    }
}
