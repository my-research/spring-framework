package com.github.dhslrl321.domain.audit;

import com.github.dhslrl321.supports.DataSourceConfigs;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import javax.sql.DataSource;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class SimpleTransferAuditRepositoryTest {

    SimpleTransferAuditRepository sut;

    @BeforeEach
    void setUp() {
        DataSource dataSource = DataSourceConfigs.hikariConnectionPoolDataSource();
        sut = new SimpleTransferAuditRepository(dataSource);
    }

    @Test
    @DisplayName("save and query")
    void name() {
        TransferAudit audit = TransferAudit.instantiate(1, 2, 3, 100, LocalDateTime.now());

        sut.save(audit);
        TransferAudit actual = sut.findBy(1);

        assertThat(actual).isEqualTo(audit);
    }

    @Test
    @DisplayName("all")
    void name1() {
        sut.save(TransferAudit.newOne(1, 2, 100));
        sut.save(TransferAudit.newOne(1, 2, 200));
        sut.save(TransferAudit.newOne(1, 2, 300));
        sut.save(TransferAudit.newOne(1, 2, 400));

        List<TransferAudit> actual = sut.findAll();

        assertThat(actual).hasSize(4);
    }
}