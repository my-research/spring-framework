package com.github.dhslrl321.rollback_mark;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.core.JdbcTemplate;

import static com.github.dhslrl321.DataSourceConfigs.hikariConnectionPoolDataSource;
import static org.assertj.core.api.Assertions.assertThat;

public class TransactionAbortTest {
    JdbcTemplate sut = new JdbcTemplate(hikariConnectionPoolDataSource());

    @Test
    @DisplayName("queryForObject 는 단일 객체를 반환한다")
    void name() {
        Integer actual = sut.queryForObject("select count(*) from accounts", Integer.class);

        assertThat(actual).isEqualTo(0);
    }

}
