package com.example.aop_idempotency_demo.aspects;

import java.lang.annotation.*;

@Target(ElementType.METHOD)        // hanya bisa dipasang di method
@Retention(RetentionPolicy.RUNTIME) // annotation terbaca saat runtime
@Documented
public @interface Idempotency {
}
