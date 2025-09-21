package com.example.aop_idempotency_demo.repositories;

import com.example.aop_idempotency_demo.entities.TransactionReport;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface TransactionReportRepository extends JpaRepository<TransactionReport, UUID> {
    Optional<TransactionReport> findByTransactionId(String transactionId);
}
