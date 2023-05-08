package com.dhslrl321.tx.lock;

import com.dhslrl321.tx.Connections;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * shared lock
 *
 * lock 을 잡는 row 에 대해서 다른 세션이 조회는 할 수 있지만 update 는 하지 못한다
 */
public class Select_Lock_Test {

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
    @DisplayName("c1 이 commit 되기 전까지 c2 의 x lock 은 block 된다")
    void name() throws Exception {
        c1.prepareStatement("BEGIN TRANSACTION").execute();
        c1.prepareStatement("INSERT INTO member VALUES ('userA', 1000)").execute();
        c1.prepareStatement("COMMIT").execute();

        c1.prepareStatement("BEGIN TRANSACTION").execute();
        c1.prepareStatement("SELECT * FROM member WHERE id = 'userA' FOR UPDATE").execute();
        // TODO no commit

        c2.prepareStatement("SELECT * FROM member WHERE id = 'userA' FOR UPDATE").execute(); // TODO will be blocked
        // timeout exception
    }

    @Test
    @DisplayName("c1 이 commit 되면 lock 을 획득하고 연산이 수행 된다")
    void name2() throws Exception {
        c1.prepareStatement("BEGIN TRANSACTION").execute();
        c1.prepareStatement("INSERT INTO member VALUES ('userA', 1000)").execute();
        c1.prepareStatement("COMMIT").execute();

        c1.prepareStatement("BEGIN TRANSACTION").execute();
        c1.prepareStatement("SELECT * FROM member WHERE id = 'userA' FOR UPDATE").execute();
        c1.prepareStatement("COMMIT").execute();

        rs = c2.prepareStatement("SELECT * FROM member WHERE id = 'userA' FOR UPDATE").executeQuery(); // TODO will be blocked
        c2.prepareStatement("COMMIT").execute();
        rs.next();
        Assertions.assertThat(rs.getInt(2)).isEqualTo(1000);
    }
}
