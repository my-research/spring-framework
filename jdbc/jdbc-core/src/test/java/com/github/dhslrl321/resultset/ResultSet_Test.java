package com.github.dhslrl321.resultset;

import com.github.dhslrl321.ConnectionSupports;
import com.zaxxer.hikari.pool.HikariPool;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import static com.github.dhslrl321.ConnectionSupports.close;
import static com.github.dhslrl321.ConnectionSupports.connectionWith;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

/**
 * ResultSet
 * A table of data representing a database result set,
 */
public class ResultSet_Test {

    Connection connection;
    PreparedStatement statement;

    ResultSet sut;

    @BeforeEach
    void setUp() {
        connection = connectionWith("DROP TABLE IF EXISTS users CASCADE; " +
                "CREATE TABLE users (id bigint , name varchar(255), primary key (id));");
    }

    @AfterEach
    void tearDown() {
        close(connection, statement, sut);
    }

    @Test
    @DisplayName("ResultSet 은 cursor 라는 개념이 있어서 한 번 움직여줘야 한다")
    void move() throws Exception {
        sut = connection.prepareStatement("SELECT * FROM users WHERE id = 1").executeQuery();

        boolean isMoved = sut.next();// move cursor

        assertThat(isMoved).isFalse();
    }

    @Test
    @DisplayName("커서를 움직이고 column index 를 통해서 조회한다")
    void empty() throws Exception {
        statement = connection.prepareStatement("INSERT INTO users VALUES ('1', 'jangwonik')");
        statement.executeUpdate();

        sut = connection.prepareStatement("SELECT * FROM users WHERE id = 1").executeQuery();

        sut.next(); // move cursor

        assertThat(sut.getLong(1)).isEqualTo(1);
        assertThat(sut.getString(2)).isEqualTo("jangwonik");
    }

    @Test
    @DisplayName("조회할 데이터가 없는데 조회한다면 예외가 발생함")
    void empty_find() throws Exception {
        sut = connection.prepareStatement("SELECT * FROM users WHERE id = 1").executeQuery();

        assertThatThrownBy(() -> sut.getLong(1));
    }
}
