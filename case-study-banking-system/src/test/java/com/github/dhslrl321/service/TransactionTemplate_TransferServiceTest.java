package com.github.dhslrl321.service;

import com.github.dhslrl321.BankingApp;
import com.github.dhslrl321.domain.audit.AlwaysFailTransferAuditRepository;
import com.github.dhslrl321.domain.account.Account;
import com.github.dhslrl321.domain.account.AccountRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.support.TransactionTemplate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@SpringBootTest(classes = BankingApp.class)
class TransactionTemplate_TransferServiceTest {

    public static final int INITIAL_AMOUNT = 100_000;
    public static final int TRANSFER_AMOUNT = 9_000;
    TransactionTemplateTransferService sut;

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    AlwaysFailTransferAuditRepository alwaysFailAuditRepository;
    @Autowired
    TransactionTemplate tx;

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
    @DisplayName("의도적으로 unchecked 예외를 발생시키는 auditRepository")
    void name2() {
        // arrange
        // service 에 항상 예외를 던지는 auditRepository 를 주입한다
        sut = new TransactionTemplateTransferService(tx, accountRepository, alwaysFailAuditRepository);

        // act
        // 정상적으로 송금이 완료된다
        assertThatThrownBy(() -> sut.transfer(accountA.getId(), accountB.getId(), TRANSFER_AMOUNT))
                .isInstanceOf(RuntimeException.class);

        // assert
        // member A 와 B 의 잔고는 처음 그대로여야 한다
        assertThat(accountRepository.findBy(accountA.getId()).getBalance()).isEqualTo(INITIAL_AMOUNT);
        assertThat(accountRepository.findBy(accountB.getId()).getBalance()).isEqualTo(INITIAL_AMOUNT);
    }
}