package com.github.dhslrl321.controller;

import com.github.dhslrl321.domain.audit.TransferAudit;
import com.github.dhslrl321.domain.audit.SimpleTransferAuditRepository;
import com.github.dhslrl321.domain.member.Member;
import com.github.dhslrl321.domain.member.MemberRepository;
import com.github.dhslrl321.supports.Serializer;
import com.zaxxer.hikari.HikariDataSource;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.sql.DataSource;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class QueryController {

    private final DataSource dataSource;
    private final MemberRepository memberRepository;
    private final SimpleTransferAuditRepository auditRepository;

    @GetMapping(value = "/members", produces = "application/json")
    public ResponseEntity<String> members() {
        List<Member> all = memberRepository.findAll();
        String serialized = Serializer.serialize(all);
        return ResponseEntity.ok(serialized);
    }

    @GetMapping(value = "/audits", produces = "application/json")
    public ResponseEntity<String> audits() {
        List<TransferAudit> all = auditRepository.findAll();
        String serialized = Serializer.serialize(all);
        return ResponseEntity.ok(serialized);
    }

    @GetMapping(value = "/connections", produces = "application/json")
    public ResponseEntity<Map<String, Integer>> activeConnections() {
        HikariDataSource hikari = (HikariDataSource) dataSource;
        return ResponseEntity.ok(Map.of(
                "activeConnections", hikari.getHikariPoolMXBean().getActiveConnections(),
                "totalConnections", hikari.getHikariPoolMXBean().getTotalConnections()
        ));
    }
}
