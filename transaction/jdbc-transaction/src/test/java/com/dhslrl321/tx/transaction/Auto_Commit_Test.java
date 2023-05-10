package com.dhslrl321.tx.transaction;

import com.dhslrl321.tx.Connections;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.ResultSet;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Auto Commit
 *
 * 자동 커밋 모드로 sql 이 실행되는 즉시 쿼리가 실행되도록 하는 옵션이다.
 * 기본적으로 auto commit 이 활성화되어있다
 *
 * auto commit 을 활성화 하지 않으려면 `set autocommit false` 명령을 수행하거나
 * 수동으로 transaction 을 생성하고 commit 해줘야 한다
 */
public class Auto_Commit_Test {

    @BeforeEach
    void setUp() {
        Connections.initMemberTable();
    }

    @Test
    @DisplayName("auto transaction 시, sql 한 줄마다 commit 이 자동으로 수행된다")
    void auto_commit() throws Exception {
        Connection ac = Connections.newOne();
        Connection bc = Connections.newOne();

        ac.prepareStatement("SET AUTOCOMMIT TRUE").execute();
        ac.prepareStatement("INSERT INTO MEMBER VALUES ('userA', 10000)").execute();

        ResultSet rs = bc.prepareStatement("SELECT count(*) FROM MEMBER").executeQuery();
        rs.next();

        assertThat(rs.getInt(1)).isEqualTo(1);
    }


}
