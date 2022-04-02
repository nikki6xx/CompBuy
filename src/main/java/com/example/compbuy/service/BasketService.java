package com.example.compbuy.service;

import com.example.compbuy.model.Basket;
import com.example.compbuy.model.Product;
import com.example.compbuy.repository.BasketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BasketService implements BasketServiceI {

    private final BasketRepository basketRepository;

    @Autowired
    public BasketService(BasketRepository basketRepository) {
        this.basketRepository = basketRepository;
    }


    @Override
    public List<Product> getProductsById(Long id) {
        return null;
    }

    @Override
    public void save(Basket basket) {
        basketRepository.save(basket);
    }

    @Override
    public List<Basket> getAll() {
        return basketRepository.findAll();
    }

    @Override
    public Optional<Basket> findById(Long id) {
        return basketRepository.findById(id);
    }
}
