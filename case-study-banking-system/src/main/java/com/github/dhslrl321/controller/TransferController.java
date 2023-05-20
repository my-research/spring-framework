package com.github.dhslrl321.controller;

import com.github.dhslrl321.service.TransactionTemplateTransferService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class TransferController {

    private final TransactionTemplateTransferService service;

    @PostMapping(value = "/transfers")
    public ResponseEntity<Map<String, String>> join(@RequestBody Map<String, Long> body) {
        service.transfer(body.get("fromId"), body.get("toId"), body.get("amount"));
        return ResponseEntity.ok(Map.of(
                "status", "success",
                "requestedAt", LocalDateTime.now().toString())
        );
    }
}
