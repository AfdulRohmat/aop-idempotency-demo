package com.example.aop_idempotency_demo.dtos;

import com.example.aop_idempotency_demo.aspects.IdempotencyAware;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TransactionMessageDto implements IdempotencyAware {
    private String transactionId;
    private String accountNumber;
    private Double amount;

    @Override
    public String getKey() {
        return transactionId; // dipakai untuk cek idempotency
    }
}
