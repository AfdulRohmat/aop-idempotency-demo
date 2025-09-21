package com.example.aop_idempotency_demo.services;

import com.example.aop_idempotency_demo.entities.User;

import java.util.List;
import java.util.UUID;

public interface UserService {
    User createUser(User user);
    User getUserById(UUID id);
    List<User> getAllUsers();
}
