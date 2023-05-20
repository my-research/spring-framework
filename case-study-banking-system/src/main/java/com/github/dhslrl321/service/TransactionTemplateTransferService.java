package com.github.dhslrl321.service;

import com.github.dhslrl321.domain.audit.TransferAudit;
import com.github.dhslrl321.domain.audit.TransferAuditRepository;
import com.github.dhslrl321.domain.account.Account;
import com.github.dhslrl321.domain.account.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.transaction.support.TransactionTemplate;

@Primary
@Service
@RequiredArgsConstructor
public class TransactionTemplateTransferService implements TransferService {

    private final TransactionTemplate tx;
    private final AccountRepository accountRepository;
    private final TransferAuditRepository auditRepository;

    @Override
    public void transfer(long fromId, long toId, long amount) {
        tx.executeWithoutResult((status) -> {
            Account from = accountRepository.findBy(fromId);
            Account to = accountRepository.findBy(toId);

            from.minus(amount);
            to.plus(amount);

            accountRepository.updateAll(from, to);
            auditRepository.save(TransferAudit.newOne(fromId, toId, amount));
        });
    }
}
