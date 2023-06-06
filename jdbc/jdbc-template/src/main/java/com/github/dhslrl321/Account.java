package com.github.dhslrl321;

import lombok.*;

@Getter
@ToString
@AllArgsConstructor(staticName = "loadFromDb")
public class Account {
    private final long id;
    private String name;
    private long balance;
}
