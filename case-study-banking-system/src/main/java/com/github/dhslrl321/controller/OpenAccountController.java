package com.github.dhslrl321.controller;

import com.github.dhslrl321.service.etc.JoinMemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class OpenAccountController {
    private final JoinMemberService service;

    @PostMapping("/accounts")
    public ResponseEntity<Map<String, String>> open(@RequestBody Map<String, String> body) {
        String name = body.get("name");

        service.openNewAccount(name);

        return ResponseEntity.ok(Map.of(
                "status", "success",
                "requestedAt", LocalDateTime.now().toString())
        );
    }
}
