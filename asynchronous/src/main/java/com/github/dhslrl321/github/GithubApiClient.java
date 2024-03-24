package com.github.dhslrl321.github;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import static java.util.Objects.requireNonNull;
import static org.springframework.web.util.UriComponentsBuilder.fromUriString;

@Component
@RequiredArgsConstructor
public class GithubApiClient {

    private final RestTemplate restTemplate;

    public List<GithubEvent> getEvents(int perPage, int page) {
        String url = "https://api.github.com/events";

        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(MediaType.parseMediaTypes("application/vnd.github+json"));
        headers.set("X-GitHub-Api-Version", "2022-11-28");

        // Create a GET request entity
        RequestEntity<Void> requestEntity = RequestEntity
                .get(fromUriString(url)
                        .queryParam("per_page", perPage)
                        .queryParam("page", page)
                        .build()
                        .toUri())
                .headers(headers)
                .build();

        ResponseEntity<GithubEvent[]> responseEntity = restTemplate.exchange(requestEntity, GithubEvent[].class);

        // Print the response body
        return Arrays.asList(requireNonNull(responseEntity.getBody()));
    }
}
