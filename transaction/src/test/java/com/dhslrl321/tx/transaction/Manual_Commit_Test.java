package com.dhslrl321.tx.transaction;

import com.dhslrl321.tx.Connections;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.ResultSet;

import static org.assertj.core.api.Assertions.assertThat;

public class Manual_Commit_Test {
    @Test
    @DisplayName("수동 commit 모드라면 명시적으로 commit 을 호출하기 전에 sql 결과를 다른 세션에서 확인할 수 없다")
    void auto_commit_should_be_committed() throws Exception {
        Connection ac = Connections.newOne(); // tx 시작
        ac.prepareStatement("SET AUTOCOMMIT FALSE").execute();
        ac.prepareStatement("INSERT INTO MEMBER VALUES ('userA', 10000)").execute();

        // 새로운 tx 를 열고
        Connection bc = Connections.newOne();
        // ac 에서 commit 되지 않은 데이터를 읽을 수 있는가?
        ResultSet rs = bc.prepareStatement("SELECT count(*) FROM MEMBER").executeQuery();
        rs.next();

        assertThat(rs.getInt(1)).isEqualTo(0);
    }

    @Test
    @DisplayName("수동 commit 모드로 전환되면 commit 이 되어야 다른 커넥션(세션)에서 데이터를 조회할 수 있다")
    void manual_commit() throws Exception {
        Connection ac = Connections.newOne(); // tx 시작
        ac.prepareStatement("SET AUTOCOMMIT FALSE").execute();
        ac.prepareStatement("INSERT INTO MEMBER VALUES ('userA', 10000)").execute();
        ac.prepareStatement("COMMIT").execute();

        // 새로운 tx 를 열고
        Connection bc = Connections.newOne();
        // ac 에서 commit 되지 않은 데이터를 읽을 수 있는가?
        ResultSet rs = bc.prepareStatement("SELECT count(*) FROM MEMBER").executeQuery();
        rs.next();

        assertThat(rs.getInt(1)).isEqualTo(1);
    }
}
