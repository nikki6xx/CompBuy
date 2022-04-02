package com.example.compbuy.controllers;

import com.example.compbuy.model.Basket;
import com.example.compbuy.model.Product;
import com.example.compbuy.repository.ProductRepository;
import com.example.compbuy.model.User;
import com.example.compbuy.repository.BasketRepository;
import com.example.compbuy.service.BasketService;
import com.example.compbuy.service.ProductService;
import com.example.compbuy.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class ShoppingListController {

    private final BasketRepository shoppingItemRepository;

    private final UserServiceImpl userService;

    private final ProductRepository productRepository;

    private final BasketService basketService;

    private final ProductService productService;

    @Autowired
    public ShoppingListController(BasketRepository shoppingItemRepository, UserServiceImpl userService, ProductRepository productRepository, BasketService basketService, ProductService productService) {
        this.shoppingItemRepository = shoppingItemRepository;
        this.userService = userService;
        this.productRepository = productRepository;
        this.basketService = basketService;
        this.productService = productService;
    }
    @PostMapping("/busket")
    public String addToBasket(Model model, Basket basket,Product product,User user){
        basketService.save(basket);
        model.addAttribute("basket",basket);
        model.addAttribute("product",product);
        model.addAttribute("user",user);
        return "busket" ;
    }



    @GetMapping("/busket")
    public String getPage(Model model){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findByUsername(authentication.getName()).get();
        Basket basket = user.getBasket();
        List<Product> productList = basket.getProduct();
        model.addAttribute("user",user);
        model.addAttribute("products",productList);
        return "busket";
    }
    @PostMapping("/busket/item{id}")
    public String deleteProduct(@PathVariable("id") Long id){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findByUsername(authentication.getName()).get();
        Product product = productService.findById(id).get();
        Basket basket = user.getBasket();
        basket.deletePr(product);
        basketService.save(basket );
        return "redirect:/busket";
    }

    /*@PostMapping
    public String newShopItem(Product product){
        shoppingItemRepository.save(product);
        return "";
    }*/
}
