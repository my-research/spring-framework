package com.github.dhslrl321.domain.account;

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
public class AccountRepository {

    public static final String TABLE_NAME = "accounts";
    private final ConnectionSupports supports;

    public AccountRepository(DataSource dataSource) {
        supports = new ConnectionSupports(dataSource);
    }

    public List<Account> findAll() {

        try {
            Connection conn = supports.connection();

            PreparedStatement statement = conn.prepareStatement("SELECT * FROM accounts");

            ResultSet rs = statement.executeQuery();
            List<Account> accounts = new ArrayList<>();
            while (rs.next()) {
                accounts.add(Account.instantiate(
                        rs.getLong(1),
                        rs.getString(2),
                        rs.getLong(3)));
            }

            supports.release(conn, statement, rs);
            return accounts;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Account findBy(long id) {
        Connection conn = supports.connection();
        PreparedStatement statement = null;
        try {
            if (!supports.isExist(TABLE_NAME, id)) {
                throw new IllegalArgumentException("존재하지 않는 유저");
            }
            statement = conn.prepareStatement("SELECT * FROM accounts WHERE id = ?");
            statement.setLong(1, id);

            ResultSet rs = supports.executeQuery(statement);
            Account account = Account.instantiate(rs.getLong(1), rs.getString(2), rs.getLong(3));

            supports.release(conn, statement, rs);
            return account;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            supports.release(conn, statement);
        }
    }

    public void updateAll(Account... accounts) {
        Arrays.stream(accounts).forEach(this::update);
    }

    public void save(Account account) {
        Connection conn = supports.connection();
        PreparedStatement statement = null;

        try {
            if (supports.isExist(TABLE_NAME, account.getId())) {
                throw new IllegalStateException("이미 존재하는 유저");
            }

            statement = conn.prepareStatement("INSERT INTO accounts VALUES (?, ?, ?)");
            statement.setLong(1, account.getId());
            statement.setString(2, account.getName());
            statement.setLong(3, account.getBalance());

            statement.executeUpdate();
            supports.release(conn, statement);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            supports.release(conn, statement);
        }
    }

    public void update(Account account) {

        Connection conn = supports.connection();
        PreparedStatement statement = null;

        try {
            statement = conn.prepareStatement("UPDATE accounts SET balance = ? WHERE id = ?");
            statement.setLong(1, account.getBalance());
            statement.setLong(2, account.getId());

            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            supports.release(conn, statement);
        }
    }
}
