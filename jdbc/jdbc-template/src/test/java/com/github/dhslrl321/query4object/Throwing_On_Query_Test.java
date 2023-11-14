package com.github.dhslrl321.query4object;

import com.github.dhslrl321.Account;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

import java.sql.ResultSet;
import java.sql.SQLException;

import static com.github.dhslrl321.DataSourceConfigs.hikariConnectionPoolDataSource;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class Throwing_On_Query_Test {

    JdbcTemplate sut = new JdbcTemplate(hikariConnectionPoolDataSource());

    @Test
    @DisplayName("만약 조회하려는 대상이 여러건이면 예외를 반환한다")
    void name() {
        sut.update("insert into accounts values (1, 'jang', 9900)");
        sut.update("insert into accounts values (2, 'heo', 2200)");

        assertThatThrownBy(() -> sut.queryForObject("select * from accounts", Throwing_On_Query_Test::instantiate))
                .isInstanceOf(IncorrectResultSizeDataAccessException.class)
                .hasMessage("Incorrect result size: expected 1, actual 2");
    }

    @Test
    @DisplayName("만약 조회하려는 대상이 존재하지 않으면 예외를 반환한다")
    void name2() {
        assertThatThrownBy(() -> sut.queryForObject("select * from accounts", Throwing_On_Query_Test::instantiate))
                .isInstanceOf(IncorrectResultSizeDataAccessException.class)
                .hasMessage("Incorrect result size: expected 1, actual 0");
    }

    private static Account instantiate(ResultSet rs, int rowNum) throws SQLException {
            return Account.loadFromDb(rs.getLong(1), rs.getString(2), rs.getLong(3));
    }
}
