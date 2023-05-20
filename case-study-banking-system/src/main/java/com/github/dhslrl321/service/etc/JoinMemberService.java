package com.github.dhslrl321.service.etc;

import com.github.dhslrl321.domain.account.Account;
import com.github.dhslrl321.domain.account.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class JoinMemberService {

    public static final int INITIAL_AMOUNT = 100_000;
    private final AccountRepository repository;

    public void openNewAccount(String name) {
        Account account = Account.newOne(name, INITIAL_AMOUNT);
        repository.save(account);
    }
}
