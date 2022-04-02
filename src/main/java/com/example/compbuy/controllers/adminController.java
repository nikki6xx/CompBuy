package com.example.compbuy.controllers;

import com.example.compbuy.model.Product;
import com.example.compbuy.model.User;
import com.example.compbuy.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.imageio.ImageIO;
import javax.validation.Valid;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;

@Controller
public class adminController {

    private final ProductService productService;

    @Autowired
    public adminController(ProductService productService) {
        this.productService = productService;
    }


    @GetMapping("/admin/addProduct")
    public String getPage( User user ,Model model){
        model.addAttribute("user",user);
        model.addAttribute("product",new Product());
        return "admin";
    }


    @PostMapping("/admin/addProduct")
    public String add(@ModelAttribute("product") @Valid  Product product) throws IOException {
        productService.save(product);
        System.out.println(productService.findById(1L).get().toString());
        return "redirect:/admin/addProduct";
    }
    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable("id")Long id){
        productService.delete(id);
        return "redirect:/admin";
    }




}
