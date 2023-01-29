package com.github.dhslrl321.transaction.repository;

import com.github.dhslrl321.transaction.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserJpaRepository extends JpaRepository<User, Long> {
}
