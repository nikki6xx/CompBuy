package com.example.compbuy.repository;

import com.example.compbuy.model.Product;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.example.compbuy.model.Basket;

import java.util.List;
import java.util.Optional;


@Repository
public interface BasketRepository extends JpaRepository<Basket,Long> {
}
