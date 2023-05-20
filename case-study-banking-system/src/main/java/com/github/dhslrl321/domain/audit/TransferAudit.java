package com.github.dhslrl321.domain.audit;

import com.github.dhslrl321.supports.IdGenerator;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@ToString
@EqualsAndHashCode
@AllArgsConstructor(access = AccessLevel.PROTECTED, staticName = "instantiate")
public class TransferAudit {

    public static TransferAudit newOne(long from, long to, long amount) {
        long id = IdGenerator.gen();
        return new TransferAudit(id, from, to, amount, LocalDateTime.now());
    }

    private final long id;
    private final long from;
    private final long to;
    private final long amount;
    private LocalDateTime createdAt;

}
