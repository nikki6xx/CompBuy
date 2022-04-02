package com.example.compbuy.model;

import lombok.*;
import org.hibernate.annotations.Cascade;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.List;


@Getter
@Setter
@EqualsAndHashCode
@AllArgsConstructor
@Entity
@Table(name = "users")
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
    @Column(name = "ROLE")
    private String role;


    @Column(name = "first_name")
    private String name;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "home")
    private String home;
    private String postcode;
    private String MbNumber;


    @OneToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JoinColumn(name ="basket_id")
    private Basket basket;

    @OneToMany(mappedBy = "user")
    private List<Order> order;



    public User() {

    }


    @Transient
    private boolean isLog = isUserLoggedIn();



    public boolean isUserLoggedIn() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return auth!=null && auth.isAuthenticated() && auth.getPrincipal() instanceof UserDetails;
    }
}
