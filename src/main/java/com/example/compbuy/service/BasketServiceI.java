package com.example.compbuy.service;

import com.example.compbuy.model.Basket;
import com.example.compbuy.model.Product;
import com.example.compbuy.model.User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface BasketServiceI {
    List<Product> getProductsById(Long id);
    void save(Basket basket);
    List<Basket> getAll();
    Optional<Basket> findById(Long id);

}
