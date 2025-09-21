package com.example.aop_idempotency_demo.aspects;

import com.example.aop_idempotency_demo.entities.IdempotencyKey;
import com.example.aop_idempotency_demo.repositories.IdempotencyKeyRepository;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Slf4j
@Aspect
@Component
@RequiredArgsConstructor
public class IdempotencyAspect {

    private final IdempotencyKeyRepository idempotencyKeyRepository;

    @Around("@annotation(com.example.aop_idempotency_demo.aspects.Idempotency) && args(idempotencyAware)")
    public Object handle(ProceedingJoinPoint joinPoint, IdempotencyAware idempotencyAware) throws Throwable {
        try {
            idempotencyKeyRepository.save(
                    IdempotencyKey.builder()
                            .key(idempotencyAware.getKey())
                            .endpoint(joinPoint.getSignature().toShortString())
                            .requestHash("N/A") // bisa ditambah hash body kalau mau lebih strict
                            .createdAt(LocalDateTime.now())
                            .build()
            );

            return joinPoint.proceed();

        } catch (DataIntegrityViolationException e) {
            log.warn("=============== Duplicate request detected, key={}", idempotencyAware.getKey());
            // return null bisa diganti throw custom exception biar konsisten dengan GlobalExceptionHandler
            throw new IllegalStateException("=============== Duplicate request detected for key=" + idempotencyAware.getKey());
        }
    }
}
