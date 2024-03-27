package com.github.dhslrl321.poc;

import com.github.dhslrl321.github.GithubFetcher;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class PocTest {

    @Autowired
    AnyService anyService;

    @Autowired
    GithubFetcher githubFetcher;

    @Test
    void name() throws Exception {
        anyService.doAny();
        anyService.doAny();
        anyService.doAny();
        anyService.doAny();
        anyService.doAny();
    }
}
