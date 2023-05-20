package com.github.dhslrl321.service.etc;

import com.github.dhslrl321.domain.audit.TransferAudit;
import com.github.dhslrl321.domain.audit.SimpleTransferAuditRepository;
import com.github.dhslrl321.domain.member.Member;
import com.github.dhslrl321.domain.member.MemberRepository;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class QueryService {

    private final MemberRepository memberRepository;
    private final SimpleTransferAuditRepository auditRepository;

    public List<Member> members() {
        return memberRepository.findAll();
    }

    public List<TransferAudit> audits() {
        return auditRepository.findAll();
    }

}
