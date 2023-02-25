package com.github.dhslrl321.persist.repository;

import com.github.dhslrl321.persist.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
}
