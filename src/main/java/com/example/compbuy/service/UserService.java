package com.example.compbuy.service;

import com.example.compbuy.model.User;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface UserService {
    void save(User user);
    Optional<User> findByUsername(String username);
}
