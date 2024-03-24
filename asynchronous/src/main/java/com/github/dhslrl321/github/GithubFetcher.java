package com.github.dhslrl321.github;

import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class GithubFetcher {
    private final GithubApiClient client;

    @Async
    public List<String> fetch() {
        List<GithubEvent> events = client.getEvents(2, 1);

        return events.stream().map(it -> it.id).collect(Collectors.toList());
    }

    @Async
    public CompletableFuture<List<String>> fetchRequest() {
        List<GithubEvent> events = client.getEvents(2, 1);

        return CompletableFuture.completedFuture(events.stream().map(it -> it.id).collect(Collectors.toList()));
    }
}
