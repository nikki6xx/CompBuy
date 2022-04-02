package com.example.compbuy.service;

import com.example.compbuy.model.User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface UserService {
    void update(User user1,User user2);
    void save(User user);
    List<User> getAll();
    Optional<User> findByUsername(String username);
}
