package com.example.aop_idempotency_demo.services.impl;

import com.example.aop_idempotency_demo.aspects.Idempotency;
import com.example.aop_idempotency_demo.dtos.TransactionMessageDto;
import com.example.aop_idempotency_demo.entities.TransactionReport;
import com.example.aop_idempotency_demo.repositories.TransactionReportRepository;
import com.example.aop_idempotency_demo.services.ReportService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ReportServiceImpl implements ReportService {

    private final TransactionReportRepository reportRepository;

    @Override
    @Idempotency
    public TransactionReport generateReport(TransactionMessageDto message) {
        log.info("Generating report for transaction {}", message.getTransactionId());

        TransactionReport report = TransactionReport.builder()
                .transactionId(message.getTransactionId())
                .accountNumber(message.getAccountNumber())
                .amount(message.getAmount())
                .processedAt(LocalDateTime.now())
                .build();

        return reportRepository.save(report);
    }

    @Override
    public List<TransactionReport> getAllReports() {
        return reportRepository.findAll();
    }
}
