package com.github.dhslrl321.definition;

import org.junit.jupiter.api.Test;
import org.springframework.transaction.support.DefaultTransactionDefinition;

public class DefaultTransactionDefinition_Test {
    @Test
    void name() {
        DefaultTransactionDefinition sut = new DefaultTransactionDefinition();

        sut.setName("my-tx");
        sut.setReadOnly(false);
        sut.setTimeout(1000);
    }
}
