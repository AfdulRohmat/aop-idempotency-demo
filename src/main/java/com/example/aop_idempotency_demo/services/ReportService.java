package com.example.aop_idempotency_demo.services;

import com.example.aop_idempotency_demo.dtos.TransactionMessageDto;
import com.example.aop_idempotency_demo.entities.TransactionReport;

import java.util.List;

public interface ReportService {
    void generateReport(TransactionMessageDto message);
    List<TransactionReport> getAllReports();
}
