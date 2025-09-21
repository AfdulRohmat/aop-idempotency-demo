package com.example.aop_idempotency_demo.aspects;

public interface IdempotencyAware {
    /**
     * Returns a unique key representing this request, used for idempotency checks.
     */
    String getKey();
}
