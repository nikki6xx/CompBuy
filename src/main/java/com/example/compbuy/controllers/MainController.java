package com.example.compbuy.controllers;

import com.example.compbuy.model.Basket;
import com.example.compbuy.model.Product;
import com.example.compbuy.model.User;
import com.example.compbuy.service.BasketService;
import com.example.compbuy.service.ProductService;
import com.example.compbuy.service.UserDetailService;

import com.example.compbuy.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import javax.xml.ws.Response;
import java.security.Principal;
import java.util.ArrayList;


@Controller
public class MainController {

    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private UserDetailService userDetailsService;

    @Autowired
    private ProductService productService;


    @Autowired
    private BasketService basketService;

    @GetMapping("/registration")
    public ModelAndView getRegPAge(){
        ModelAndView mav = new ModelAndView("register");
        mav.addObject("user",new User());
        return mav;
    }
    @PostMapping("/registration")
    public String createUser(@ModelAttribute("user") @Valid User user, BindingResult bindingResult,Model model){
        System.out.println(bindingResult.toString());
        System.out.println(user.getUsername());
        if (!userService.findByUsername(user.getUsername()).isEmpty()) {
            model.addAttribute("logError",true);
            return "register";
        }
        if (bindingResult.hasErrors()) return "register";
        user.setRole("USER");
        Basket basket = new Basket();
        basket.setUser(user);
        user.setBasket(basket);
        basket.setProduct(new ArrayList<>());
        userService.save(user);
        basketService.save(basket);
        return "redirect:/login";
    }




    @GetMapping("/")
    public ModelAndView getStartPage(@ModelAttribute("user") User user){
        ModelAndView mav = new ModelAndView("start");
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        System.out.println(user.isUserLoggedIn());
        return mav;
    }

    @GetMapping("/login")
    public String getloginPage(@RequestParam(value = "error" ,required = false) boolean error, Model model,Authentication a){
        model.addAttribute(new User());
        model.addAttribute("error",error);
        model.addAttribute("auth", a);
        return "login";
    }


    @PostMapping("/loginError" )
    public String erLog(){
        return "redirect:/login?error=true";
    }

    @GetMapping("/logout")
    public String exit(HttpServletRequest request, HttpServletResponse response){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        if (auth != null){
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return "redirect:/";
    }


}
