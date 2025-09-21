package com.example.aop_idempotency_demo.controllers;

import com.example.aop_idempotency_demo.entities.TransactionReport;
import com.example.aop_idempotency_demo.services.ReportService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reports")
@RequiredArgsConstructor
public class ReportController {

    private final ReportService reportService;

    @GetMapping
    public ResponseEntity<List<TransactionReport>> getAllReports() {
        List<TransactionReport> reports = reportService.getAllReports();
        return ResponseEntity.ok(reports);
    }
}
