package com.github.dhslrl321.playground;

import com.likelen.identifier.core.LongValueGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SomeService {
    private final LongValueGenerator generator;

    public Long getId() {
        return generator.gen();
    }
}
