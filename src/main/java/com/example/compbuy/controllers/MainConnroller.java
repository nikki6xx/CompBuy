package com.example.compbuy.controllers;

import com.example.compbuy.model.User;
import com.example.compbuy.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;


@Controller
public class MainConnroller {

    @Autowired
    private UserServiceImpl userService;

    @GetMapping("/registration")
    public ModelAndView getRegPAge(){
        ModelAndView mav = new ModelAndView("register");
        mav.addObject("user",new User());
        return mav;
    }
    @PostMapping("/registration")
    public String createUser(@ModelAttribute("user") @Valid User user, BindingResult bindingResult){
        System.out.println(bindingResult.toString());

        if (bindingResult.hasErrors()) return "register";
        userService.save(user);
        return "register";
    }




    @GetMapping("/login")
    public ModelAndView getloginPage(){
        ModelAndView mav = new ModelAndView("login");
        mav.addObject("user",new User());
        return mav;
    }
    @PostMapping("/login")
    public String loginPage(@ModelAttribute("user") @Valid User user){
        User user1 = userService.findByUsername(user.getUsername()).get();
        if (user1.getUsername().equals(user.getUsername()) && user1.getPassword().equals(user.getPassword())){
            return "start";
        }

        return "redirect:/";
    }

}
