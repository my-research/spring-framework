package com.github.dhslrl321.transaction.repository;

import com.github.dhslrl321.transaction.entity.AccessHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccessHistoryRepository extends JpaRepository<AccessHistory, Long> {
}
