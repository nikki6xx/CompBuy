package com.example.compbuy.model;

import lombok.*;
import org.hibernate.*;
import org.springframework.stereotype.Component;


import javax.persistence.*;
import javax.validation.constraints.*;


@Getter
@Setter
@ToString
@EqualsAndHashCode
@AllArgsConstructor
@Entity
@Table(name = "USER")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name= "USERNAME")

    @Size(min = 5, message = "username must be more than 5 chars")
    private String username;

    @Column(name = "PASSWORD")
    @NotBlank
    @Size(min = 5,message = "password > 5 must be")
    private String password;

    private String confirmPassword;


    @Column(name = "EMAIL")
    @Email
    private String email;
    private String role;

    public User() {

    }
}
