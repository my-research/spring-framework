package com.dhslrl321.tx.transaction;

import com.dhslrl321.tx.Connections;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import static org.assertj.core.api.Assertions.assertThat;

public class Rollback_Transaction_Test {
    Connection c1;
    Connection c2;

    ResultSet rs;

    @BeforeEach
    void setUp() throws SQLException {
        Connections.initMemberTable();

        c1 = Connections.newOne();
        c2 = Connections.newOne();
    }

    @AfterEach
    void tearDown() throws SQLException {
        c1.close();
        c2.close();
        rs.close();
    }

    @Test
    @DisplayName("트랜잭션이 rollback 되면 staged data 가 사라진다")
    void name() throws SQLException {
        c1.prepareStatement("BEGIN TRANSACTION;").execute();
        c1.prepareStatement("INSERT INTO member VALUES ('userA', 9900)").execute();
        c1.prepareStatement("ROLLBACK").execute();

        rs = c2.prepareStatement("SELECT count(*) FROM member").executeQuery();

        rs.next();
        assertThat(rs.getInt(1)).isEqualTo(0);
    }
}
