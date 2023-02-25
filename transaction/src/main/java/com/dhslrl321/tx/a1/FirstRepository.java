package com.dhslrl321.tx.a1;

import org.springframework.data.jpa.repository.JpaRepository;

public interface FirstRepository extends JpaRepository<FirstEntity, Long> {
    FirstEntity findByName(String name);
}
