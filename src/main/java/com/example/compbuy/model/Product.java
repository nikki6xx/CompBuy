package com.example.compbuy.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Set;

@Getter
@Setter
@ToString
@Entity
@Table(name = "produчcts")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    private String type;

    private String text;


    @Column(name = "price")
    private double price;


    @Column(name = "name")
    private String name;


    @Transient
    private  byte[] image;


    @ManyToMany(mappedBy = "product", fetch = FetchType.EAGER)
    private Set<Basket> baskets;


    public Product() {
    }
}
