package com.example.aop_idempotency_demo.repositories;

import com.example.aop_idempotency_demo.entities.IdempotencyKey;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IdempotencyKeyRepository extends JpaRepository<IdempotencyKey, String> {
    boolean existsByKey(String key);
}
