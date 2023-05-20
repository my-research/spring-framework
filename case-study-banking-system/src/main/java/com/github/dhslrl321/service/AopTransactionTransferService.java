package com.github.dhslrl321.service;

import com.github.dhslrl321.domain.audit.AlwaysFailTransferAuditRepository;
import com.github.dhslrl321.domain.audit.TransferAudit;
import com.github.dhslrl321.domain.account.Account;
import com.github.dhslrl321.domain.account.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Primary
@Service
@RequiredArgsConstructor
public class AopTransactionTransferService implements TransferService {

    private final AccountRepository accountRepository;
    private final AlwaysFailTransferAuditRepository auditRepository;

    @Override
    @Transactional
    public void transfer(long fromId, long toId, long amount) {
        Account from = accountRepository.findBy(fromId);
        Account to = accountRepository.findBy(toId);

        from.minus(amount);
        to.plus(amount);

        accountRepository.updateAll(from, to);
        auditRepository.save(TransferAudit.newOne(fromId, toId, amount));
    }
}
