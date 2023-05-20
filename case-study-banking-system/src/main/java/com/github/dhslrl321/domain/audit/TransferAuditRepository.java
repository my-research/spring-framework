package com.github.dhslrl321.domain.audit;

import java.util.List;

public interface TransferAuditRepository {

    List<TransferAudit> findAll();

    TransferAudit findBy(long id);

    void save(TransferAudit transferAudit);
}
