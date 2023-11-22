package com.github.dhslrl321.soccer.domain;

import lombok.*;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Player {
    String id;
    String teamId;
    String name;

    public Player(String name) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
    }
}
