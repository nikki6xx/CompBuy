package com.example.compbuy.controllers;

import com.example.compbuy.model.User;
import com.example.compbuy.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserServiceImpl userService;





    @GetMapping("")
    public ModelAndView test1(@ModelAttribute("user ") User user){
        return null;
    }

    @GetMapping("/profile")
    public ModelAndView profile(User user){
        ModelAndView mav = new ModelAndView("profile");
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        user = userService.findByUsername(authentication.getName()).get();
        System.out.println(user.toString());
        mav.addObject("user",user);
        return mav;
    }
    @PostMapping("/profile")
    public String ref(@ModelAttribute("user") User user){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User orUser= userService.findByUsername(authentication.getName()).get();
        orUser.setName(user.getName());
        orUser.setEmail(user.getEmail());
        orUser.setHome(user.getHome());
        orUser.setLastName(user.getLastName());
        orUser.setMbNumber(user.getMbNumber());
        orUser.setPostcode(user.getPostcode());
        userService.save(orUser);
        return "redirect:/user/profile";
    }


    @GetMapping("user/basket")
    public void getBasket(){

    }





}
