package com.github.dhslrl321.query;

import com.github.dhslrl321.Account;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.core.JdbcTemplate;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import static com.github.dhslrl321.DataSourceConfigs.hikariConnectionPoolDataSource;
import static org.assertj.core.api.Assertions.assertThat;

public class QueryTest {
    JdbcTemplate sut = new JdbcTemplate(hikariConnectionPoolDataSource());

    @Test
    @DisplayName("query() 는 기본적으로 복수 건을 조회할 때 사용한다")
    void name() {
        sut.update("insert into accounts values (1, 'jang', 9900)");
        sut.update("insert into accounts values (2, 'heo', 2200)");

        List<Account> actual = sut.query("select * from accounts", QueryTest::instantiate);

        assertThat(actual).hasSize(2);
    }

    private static Account instantiate(ResultSet rs, int rowNum) throws SQLException {
        return Account.loadFromDb(rs.getLong(1), rs.getString(2), rs.getLong(3));
    }
}
