package com.github.dhslrl321.domain.member;

import com.github.dhslrl321.supports.IdGenerator;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode
@AllArgsConstructor(access = AccessLevel.PROTECTED, staticName = "instantiate")
public class Member {

    public static Member newOne(String name, long balance) {
        return new Member(IdGenerator.gen(), name, balance);
    }

    private final long id;
    private final String name;
    private long balance;

    public void minus(long amount) {
        long rest = balance - amount;
        if (rest < 0) {
            throw new IllegalStateException("잔액이 부족합니다");
        }
        balance -= amount;
    }

    public void plus(long amount) {
        balance += amount;
    }
}
