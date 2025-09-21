package com.example.aop_idempotency_demo.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "idempotency_keys")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class IdempotencyKey {

    @Id
    @Column(nullable = false, unique = true)
    private String key;

    @Column(nullable = false)
    private String requestHash; // optional: hash body request untuk validasi

    @Column(nullable = false)
    private LocalDateTime createdAt;

    @Column(nullable = false)
    private String endpoint; // misalnya "/api/users"
}
