package com.github.dhslrl321;

import com.github.dhslrl321.github.GithubFetcher;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class GithubFetcherTest {

    @Autowired
    GithubFetcher sut;


    @Test
    @DisplayName("@Async 어노테이션 메서드의 반환값은 Future type 이 아니면 null 을 반환한다")
    void name() {
        List<String> actual = sut.fetch();

        assertThat(actual).isNull();
    }

    @Test
    @DisplayName("@Async 어노테이션은 Future Type 이어야 한다")
    void name2() throws ExecutionException, InterruptedException {
        CompletableFuture<List<String>> future = sut.fetchRequest();

        List<String> actual = future.exceptionally(ex -> List.of("empty")).get();

        assertThat(actual).hasSize(2);
    }
}