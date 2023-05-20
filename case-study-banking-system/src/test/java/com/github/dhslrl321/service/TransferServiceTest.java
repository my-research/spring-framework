package com.github.dhslrl321.service;

import com.github.dhslrl321.BankingApp;
import com.github.dhslrl321.domain.audit.AlwaysFailTransferAuditRepository;
import com.github.dhslrl321.domain.audit.SimpleTransferAuditRepository;
import com.github.dhslrl321.domain.audit.TransferAudit;
import com.github.dhslrl321.domain.member.Member;
import com.github.dhslrl321.domain.member.MemberRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.support.TransactionTemplate;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(classes = BankingApp.class)
public class TransferServiceTest {


    public static final int INITIAL_AMOUNT = 100_000;
    public static final int TRANSFER_AMOUNT = 9_000;

    @Autowired
    TransactionTemplateTransferService sut;

    @Autowired
    MemberRepository memberRepository;
    @Autowired
    SimpleTransferAuditRepository auditRepository;

    @Autowired
    PlatformTransactionManager tx;

    Member memberA;
    Member memberB;

    @BeforeEach
    void setUp() {
        memberA = Member.newOne("jang", 100_000);
        memberB  = Member.newOne("heo", 100_000);

        memberRepository.save(memberA);
        memberRepository.save(memberB);
    }

    @Test
    @DisplayName("송금하면 발신 계좌의 금액은 차감되고 수신 계좌의 금액은 증가한다. 그리고 audit 로그가 추가된다")
    void name() {

        // act
        // 정상적으로 송금이 완료된다
        sut.transfer(memberA.getId(), memberB.getId(), TRANSFER_AMOUNT);

        // assertion
        // A 와 B 의 잔고는 각각 금액이 차감되거나 증가해야 한다
        assertThat(memberRepository.findBy(memberA.getId()).getBalance())
                .isEqualTo(INITIAL_AMOUNT - TRANSFER_AMOUNT);
        assertThat(memberRepository.findBy(memberB.getId()).getBalance())
                .isEqualTo(INITIAL_AMOUNT + TRANSFER_AMOUNT);

        // audit 에는 송금 이력이 저장된다
        TransferAudit audit = auditRepository.findAll().get(0);
        assertThat(audit.getFrom()).isEqualTo(memberA.getId());
        assertThat(audit.getTo()).isEqualTo(memberB.getId());
        assertThat(audit.getAmount()).isEqualTo(TRANSFER_AMOUNT);
    }
}
