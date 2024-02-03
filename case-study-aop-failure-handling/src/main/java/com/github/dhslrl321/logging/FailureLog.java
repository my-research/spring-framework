package com.github.dhslrl321.logging;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Data
@Entity(name = "failure_log")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FailureLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String keyName;
    private String keyValue;
    private String payload;
    private String exception;
    private LocalDateTime occurredAt;
}
