package com.example.compbuy.service;

import com.example.compbuy.model.User;
import com.example.compbuy.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Service
@AllArgsConstructor
public class UserDetailService implements org.springframework.security.core.userdetails.UserDetailsService{

    @Autowired
    private UserServiceImpl userService;


    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = userService
                .findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not Founded"));

        Set<GrantedAuthority> grantedAuthorities = new HashSet();

        grantedAuthorities.add(new SimpleGrantedAuthority("USER"));
        grantedAuthorities.add(new SimpleGrantedAuthority("ADMIN"));
        return new org.springframework.security.core.userdetails.User(user.getUsername(),user.getPassword(),grantedAuthorities);
    }
}
