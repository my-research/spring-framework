package com.github.dhslrl321.supports;

import java.time.Instant;
import java.util.Random;
import java.util.stream.Collectors;

public class IdGenerator {
    public static long gen() {
        String prefix = getRandom(3);
        String postfix = getRandom(3);

        return Long.parseLong(prefix + timestamp() + postfix);
    }

    private static String timestamp() {
        long l = Instant.now().toEpochMilli();
        return String.valueOf(l).substring(5, 13);
    }

    private static String getRandom(int size) {
        return new Random()
                .ints(size, 1, 10)
                .mapToObj(String::valueOf)
                .collect(Collectors.joining());
    }
}
