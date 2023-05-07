package com.dhslrl321.tx.transaction;

import com.dhslrl321.tx.Connections;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * 수동 commit 을 실행하는 방법으로 명시적으로 commit 을 호출하는 방법이 있고
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
    void name() throws Exception {
        c1.prepareStatement("BEGIN TRANSACTION;").execute();
        c1.prepareStatement("INSERT INTO MEMBER VALUES ('userA', 1000);").execute();

        rs = c2.prepareStatement("SELECT count(*) FROM MEMBER;").executeQuery();
        rs.next();
        assertThat(rs.getInt(1)).isEqualTo(0);

        c1.prepareStatement("COMMIT;").execute();

        rs = c2.prepareStatement("SELECT count(*) FROM MEMBER;").executeQuery();
        rs.next();
        assertThat(rs.getInt(1)).isEqualTo(1);
    }
}
