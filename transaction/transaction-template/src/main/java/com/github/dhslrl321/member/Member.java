package com.github.dhslrl321.member;

import lombok.AccessLevel;
import lombok.Getter;

import java.time.Instant;

@Getter(AccessLevel.PROTECTED)
public class Member {

    public static Member newOne(String name, long balance) {
        long id = Instant.now().toEpochMilli();
        return new Member(id, name, balance);
    }

    public static Member instantiate(long id, String name, long balance) {
        return new Member(id, name, balance);
    }

    private final long id;
    private final String name;
    private long balance;

    private Member(long id, String name, long balance) {
        this.id = id;
        this.name = name;
        this.balance = balance;
    }

    public void transferred(long amount) {
        balance -= amount;
    }
}
