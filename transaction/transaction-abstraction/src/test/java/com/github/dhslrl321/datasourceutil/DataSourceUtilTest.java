package com.github.dhslrl321.datasourceutil;

import com.github.dhslrl321.Fixtures;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;

import static com.github.dhslrl321.Fixtures.SQL;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * DataSourceUtil
 * - supports for spring-managed transaction
 *
 * The DataSourceUtils class also provides methods
 * for managing transactions manually,
 * without using the TransactionManager.
 *
 * For example, it provides a method called "doGetConnection"
 * that retrieves a connection from the DataSource and starts a transaction manually.
 * This can be useful in cases where the application needs
 * more fine-grained control over transactions,
 * such as when dealing with multiple databases or non-transactional resources.
 */
public class DataSourceUtilTest {

    DataSource dataSource = new DriverManagerDataSource(Fixtures.URL, Fixtures.USERNAME, Fixtures.PASSWORD);

    @BeforeEach
    void setUp() throws Exception{
        Connection connection = DataSourceUtils.getConnection(dataSource);
        connection.prepareStatement(SQL).execute();
    }

    @Test
    void name() throws Exception {

        Connection connection = DataSourceUtils.getConnection(dataSource);

        ResultSet rs = connection.prepareStatement("SELECT count(*) FROM member").executeQuery();
        rs.next();

        assertThat(rs.getInt(1)).isEqualTo(0);
    }
}
