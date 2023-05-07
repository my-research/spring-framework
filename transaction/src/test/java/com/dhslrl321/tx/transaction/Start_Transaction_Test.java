package com.dhslrl321.tx.transaction;

import com.dhslrl321.tx.Connections;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * 명시적으로 transaction 을 실행시켜 manual commit 모드로 만들 수 있다
 */
public class Start_Transaction_Test {

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
    @DisplayName("begin transaction 을 통해 명시적으로 트랜잭션을 실행시킨다")
    void name() throws Exception {
        c1.prepareStatement("BEGIN TRANSACTION;").execute();
        c1.prepareStatement("INSERT INTO MEMBER VALUES ('userA', 1000);").execute();

        rs = c2.prepareStatement("SELECT count(*) FROM MEMBER;").executeQuery();
        rs.next();
        // commit 되기 전에 다른 세션에서는 조회할 수 없다
        assertThat(rs.getInt(1)).isEqualTo(0);

        c1.prepareStatement("COMMIT;").execute();

        rs = c2.prepareStatement("SELECT count(*) FROM MEMBER;").executeQuery();
        rs.next();
        assertThat(rs.getInt(1)).isEqualTo(1);
    }

    @Test
    @DisplayName("begin transaction 을 통해 명시적으로 트랜잭션을 실행시킨다")
    void name2() throws Exception {
        c1.prepareStatement("BEGIN TRANSACTION;").execute();
        c1.prepareStatement("INSERT INTO MEMBER VALUES ('userA', 1000);").execute();

        // commit 되기 전에 다른 세션에서는 조회할 수 없다
        rs = c2.prepareStatement("SELECT count(*) FROM MEMBER;").executeQuery();
        rs.next();
        assertThat(rs.getInt(1)).isEqualTo(0);

        // commit 되기 전이라도 tx 를 시작한 세션에서는 조회가 가능하다
        rs = c1.prepareStatement("SELECT count(*) FROM member").executeQuery();
        rs.next();
        assertThat(rs.getInt(1)).isEqualTo(1);

        c1.prepareStatement("COMMIT;").execute();

        this.rs = c2.prepareStatement("SELECT count(*) FROM MEMBER;").executeQuery();
        this.rs.next();
        assertThat(this.rs.getInt(1)).isEqualTo(1);
    }
}
