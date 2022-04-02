package com.example.compbuy.service;

import com.example.compbuy.model.Basket;
import com.example.compbuy.model.Product;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface ProductServiceI {


    void save(Product product);
    List<Product> getAll();
    List<Product> findAllByType(String type);
    Optional<Product> findById(Long id);
    void delete(Long id);
}
