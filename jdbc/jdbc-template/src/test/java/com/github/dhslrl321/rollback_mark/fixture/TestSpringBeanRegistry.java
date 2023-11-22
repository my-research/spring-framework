package com.github.dhslrl321.rollback_mark.fixture;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.JdbcTransactionManager;

import static com.github.dhslrl321.DataSourceConfigs.hikariConnectionPoolDataSource;

@Configuration
public class TestSpringBeanRegistry {
    @Bean
    public JdbcTemplate jdbcTemplate() {
        return new JdbcTemplate(hikariConnectionPoolDataSource());
    }
}
