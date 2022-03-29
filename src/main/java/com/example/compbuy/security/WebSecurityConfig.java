package com.example.compbuy.security;

import com.example.compbuy.service.UserDetailService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity

public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailService userDetailsService;


    @Bean
    public static PasswordEncoder bCryptPasswordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().antMatchers("/","/user","/user/profile").permitAll();
        http.authorizeRequests().antMatchers("/registration","/login").permitAll();
        http.authorizeRequests().antMatchers("/logout").permitAll();
        http.csrf().disable();
        http.formLogin()
                .loginPage("/login")
                .usernameParameter("username")//
                .passwordParameter("password")
                .defaultSuccessUrl("/")
                .failureForwardUrl("/loginError")
                // Config for Logout Page
                .and().logout().logoutUrl("/logout").logoutSuccessUrl("/");
    }
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception{
        auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder());
    }
}
