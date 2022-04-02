package com.example.compbuy.model;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "orders")

public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "users_id")
    private User user;

    private BigDecimal sum;
    private String home;


    @OneToMany(cascade = CascadeType.ALL)
    private List<OrderDetail> details;

}
