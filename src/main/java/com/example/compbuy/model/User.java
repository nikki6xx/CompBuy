package com.example.compbuy.model;

import lombok.*;
import org.hibernate.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;


import javax.persistence.*;
import javax.validation.constraints.*;
import java.security.Principal;


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

    @Size(min = 3, message = "Имя пользователя должно быть больше 3")
    private String username;

    @Column(name = "PASSWORD")
    @NotBlank
    @Pattern(regexp = "(?=^.{3,9}$)((?=.*\\d)|(?=.*\\W+))(?![.\\n])(?=.*[A-Z])(?=.*[a-z]).*$", message = "(Строчные и прописные латинские буквы, цифры, спецсимволы. Минимум 3 символо и максимум 8)")
    private String password;

    @Column(name = "EMAIL")
    @Email(message = "Некорректно введен email.")
    private String email;

    private Boolean isAct = false;
    private String role;

    public User() {

    }

    private boolean isLog = isUserLoggedIn();


    public boolean isUserLoggedIn() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return auth!=null && auth.isAuthenticated() && auth.getPrincipal() instanceof UserDetails;
    }
}
