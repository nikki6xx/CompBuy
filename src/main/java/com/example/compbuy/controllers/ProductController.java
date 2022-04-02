package com.example.compbuy.controllers;

import com.example.compbuy.model.Basket;
import com.example.compbuy.model.Product;
import com.example.compbuy.model.User;
import com.example.compbuy.service.BasketService;
import com.example.compbuy.service.ProductService;
import com.example.compbuy.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@Controller
public class ProductController {

    private final ProductService productService;
    private final UserServiceImpl userService;
    private final BasketService basketService;

    @Autowired
    public ProductController(ProductService productService, UserServiceImpl userService, BasketService basketService) {
        this.productService = productService;
        this.userService = userService;
        this.basketService = basketService;
    }



    @GetMapping("/product/{id}")
    public String getPage(@PathVariable("id") Long id, User user , Model model ) throws IOException {
        Product product = productService.findById(id).get();
        model.addAttribute("product",product);
        model.addAttribute("user",user);
        return "product";
    }
    @PostMapping("/product/{id}")
    public String addPrBasket(@PathVariable("id") Long id){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Basket basket = userService.findByUsername(authentication.getName()).get().getBasket();
        basket.addPrToPrList(productService.findById(id).get());
        basketService.save(basket);
        return "redirect:/product/"+id;
    }




}
