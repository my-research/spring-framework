package com.github.dhslrl321.transaction.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.Instant;

@Entity
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class AccessHistory {

    public static AccessHistory by(String username) {
        return new AccessHistory(null, username, Instant.now());
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String by;
    private Instant createdAt;
}
