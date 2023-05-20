package com.github.dhslrl321.service.etc;

import com.github.dhslrl321.domain.member.Member;
import com.github.dhslrl321.domain.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class JoinMemberService {

    public static final int INITIAL_AMOUNT = 100_000;
    private final MemberRepository repository;

    public void join(String name) {
        Member member = Member.newOne(name, INITIAL_AMOUNT);
        repository.save(member);
    }
}
