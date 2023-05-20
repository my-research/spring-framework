package com.github.dhslrl321.service.etc;

import com.github.dhslrl321.domain.audit.TransferAudit;
import com.github.dhslrl321.domain.audit.SimpleTransferAuditRepository;
import com.github.dhslrl321.domain.account.Account;
import com.github.dhslrl321.domain.account.AccountRepository;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class QueryService {

    private final AccountRepository accountRepository;
    private final SimpleTransferAuditRepository auditRepository;

    public List<Account> members() {
        return accountRepository.findAll();
    }

    public List<TransferAudit> audits() {
        return auditRepository.findAll();
    }

}
