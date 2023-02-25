package com.github.dhslrl321.persist.repository;

import com.github.dhslrl321.persist.entity.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<OrderEntity, Long> {
}
