package com.github.dhslrl321.preparedstatement;

import com.github.dhslrl321.ConnectionSupports;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;

import static com.github.dhslrl321.ConnectionSupports.close;
import static com.github.dhslrl321.ConnectionSupports.connectionWith;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * PreparedStatement
 * An object that represents a precompiled SQL statement.
 * <p>
 * parameterized sql 을 db 에 실행할 수 있게 됨
 */
public class PreparedStatement_Test {

    Connection connection;
    PreparedStatement sut;

    @BeforeEach
    void setUp() {
        connection = connectionWith("DROP TABLE IF EXISTS users CASCADE; " +
                "CREATE TABLE users (id bigint , name varchar(255), primary key (id));");
    }

    @AfterEach
    void tearDown() {
        close(connection, sut);
    }

    @Test
    @DisplayName("'?' 를 param index 를 통해 지정할 수 있다")
    void name() throws Exception{
        sut = connection.prepareStatement("INSERT INTO users VALUES(?, ?)");

        sut.setLong(1, 999L);
        sut.setString(2, "jangwonik");

        int actual = sut.executeUpdate();

        assertThat(actual).isEqualTo(1);
    }
}
