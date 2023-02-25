package com.dhslrl321.tx.basic;

import org.springframework.data.jpa.repository.JpaRepository;

public interface FirstEntityRepository extends JpaRepository<FirstEntity, Long> {
    FirstEntity findByName(String name);
}
