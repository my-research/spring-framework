package com.github.dhslrl321.query4object;

import com.github.dhslrl321.Account;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.core.JdbcTemplate;

import java.sql.ResultSet;
import java.sql.SQLException;

import static com.github.dhslrl321.DataSourceConfigs.hikariConnectionPoolDataSource;
import static org.assertj.core.api.Assertions.assertThat;

class Query_Object_Test {

    JdbcTemplate sut = new JdbcTemplate(hikariConnectionPoolDataSource());

    @Test
    @DisplayName("queryForObject 는 단일 객체를 반환한다")
    void name() {
        Integer actual = sut.queryForObject("select count(*) from accounts", Integer.class);

        assertThat(actual).isEqualTo(0);
    }

    @Test
    @DisplayName("RowMapper 를 정의하여 커스텀 클래스타입을 인스턴스화를 시킬 수 있다")
    void name2() {
        sut.update("INSERT INTO accounts VALUES (1, 'jang', 9900)");

        Account actual = sut.queryForObject("select * from accounts", Query_Object_Test::instantiate);

        assertThat(actual).isNotNull();
        assertThat(actual.getId()).isEqualTo(1);
        assertThat(actual.getName()).isEqualTo("jang");
    }

    private static Account instantiate(ResultSet rs, int rowNum) throws SQLException {
        return Account.loadFromDb(rs.getLong(1), rs.getString(2), rs.getLong(3));
    }
}
