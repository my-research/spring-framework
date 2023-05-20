package com.github.dhslrl321.service;

import com.github.dhslrl321.domain.audit.AlwaysFailTransferAuditRepository;
import com.github.dhslrl321.domain.audit.TransferAudit;
import com.github.dhslrl321.domain.audit.TransferAuditRepository;
import com.github.dhslrl321.domain.member.Member;
import com.github.dhslrl321.domain.member.MemberRepository;
import com.github.dhslrl321.service.TransferService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Primary
@Service
@RequiredArgsConstructor
public class AopTransactionTransferService implements TransferService {

    private final MemberRepository memberRepository;
    private final AlwaysFailTransferAuditRepository auditRepository;

    @Override
    @Transactional
    public void transfer(long fromId, long toId, long amount) {
        Member from = memberRepository.findBy(fromId);
        Member to = memberRepository.findBy(toId);

        from.minus(amount);
        to.plus(amount);

        memberRepository.updateAll(from, to);
        auditRepository.save(TransferAudit.newOne(fromId, toId, amount));
    }
}
