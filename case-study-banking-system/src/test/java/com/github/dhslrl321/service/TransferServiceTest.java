package com.github.dhslrl321.service;

import com.github.dhslrl321.BankingApp;
import com.github.dhslrl321.domain.audit.SimpleTransferAuditRepository;
import com.github.dhslrl321.domain.audit.TransferAudit;
import com.github.dhslrl321.domain.account.Account;
import com.github.dhslrl321.domain.account.AccountRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.PlatformTransactionManager;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(classes = BankingApp.class)
public class TransferServiceTest {


    public static final int INITIAL_AMOUNT = 100_000;
    public static final int TRANSFER_AMOUNT = 9_000;

    @Autowired
    TransactionTemplateTransferService sut;

    @Autowired
    AccountRepository accountRepository;
    @Autowired
    SimpleTransferAuditRepository auditRepository;

    @Autowired
    PlatformTransactionManager tx;

    Account accountA;
    Account accountB;

    @BeforeEach
    void setUp() {
        accountA = Account.newOne("jang", 100_000);
        accountB = Account.newOne("heo", 100_000);

        accountRepository.save(accountA);
        accountRepository.save(accountB);
    }

    @Test
    @DisplayName("송금하면 발신 계좌의 금액은 차감되고 수신 계좌의 금액은 증가한다. 그리고 audit 로그가 추가된다")
    void name() {

        // act
        // 정상적으로 송금이 완료된다
        sut.transfer(accountA.getId(), accountB.getId(), TRANSFER_AMOUNT);

        // assertion
        // A 와 B 의 잔고는 각각 금액이 차감되거나 증가해야 한다
        assertThat(accountRepository.findBy(accountA.getId()).getBalance())
                .isEqualTo(INITIAL_AMOUNT - TRANSFER_AMOUNT);
        assertThat(accountRepository.findBy(accountB.getId()).getBalance())
                .isEqualTo(INITIAL_AMOUNT + TRANSFER_AMOUNT);

        // audit 에는 송금 이력이 저장된다
        TransferAudit audit = auditRepository.findAll().get(0);
        assertThat(audit.getFrom()).isEqualTo(accountA.getId());
        assertThat(audit.getTo()).isEqualTo(accountB.getId());
        assertThat(audit.getAmount()).isEqualTo(TRANSFER_AMOUNT);
    }
}
