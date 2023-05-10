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
import java.time.LocalDateTime;

@Entity(name = "users")
@ToString
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class User {

    public static User create(String name) {
        return new User(null, name, Instant.now());
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Instant createdAt;
}
