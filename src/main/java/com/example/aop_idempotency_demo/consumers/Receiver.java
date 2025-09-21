package com.example.aop_idempotency_demo.consumers;


import com.example.aop_idempotency_demo.dtos.TransactionMessageDto;
import com.example.aop_idempotency_demo.entities.TransactionReport;
import com.example.aop_idempotency_demo.services.ReportService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class Receiver {

    private final ReportService reportService;
    private final ObjectMapper objectMapper = new ObjectMapper();

    @KafkaListener(topics = "transactions", groupId = "transaction-group")
    public void consume(String message) {
        log.info("Raw message from Kafka: {}", message);

        try {
            TransactionMessageDto dto = objectMapper.readValue(message, TransactionMessageDto.class);
            reportService.generateReport(dto);

            log.info("Processed transaction {}", dto.getTransactionId());
        } catch (Exception e) {
            log.error("Failed to parse or process message: {}", e.getMessage());
        }
    }
}
