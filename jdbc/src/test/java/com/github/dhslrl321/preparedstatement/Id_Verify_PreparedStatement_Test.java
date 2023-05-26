package com.github.dhslrl321.preparedstatement;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import static com.github.dhslrl321.ConnectionSupports.connectionWith;
import static java.sql.Statement.RETURN_GENERATED_KEYS;
import static org.assertj.core.api.Assertions.assertThat;

public class Id_Verify_PreparedStatement_Test {

    public static final String SQL = "INSERT INTO users (name) VALUES ('jang')";
    Connection connection;
    PreparedStatement sut;

    @BeforeEach
    void setUp() {
        connection = connectionWith("DROP TABLE IF EXISTS users CASCADE; " +
                "CREATE TABLE users (id bigint AUTO_INCREMENT, name varchar(255), primary key (id));");
    }

    @Test
    @DisplayName("auto_increment 시 생성된 id 를 반환받을 수 있다")
    void name() throws Exception {
        PreparedStatement sut = connection.prepareStatement(SQL, RETURN_GENERATED_KEYS);

        sut.executeUpdate();

        ResultSet rs = sut.getGeneratedKeys();
        rs.next();

        assertThat(rs.getLong(1)).isEqualTo(1);
    }
}
