package com.example.compbuy.model;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@ToString
@Table(name = "buskets")
public class Basket {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @OneToOne()
    @JoinColumn(name="user_id")
    private User user;


    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name ="basket_product",
            joinColumns = @JoinColumn(name="basket_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id")
    )private List<Product> product = new ArrayList<>();

    public void addPrToPrList(Product product){
        this.product.add(product);
    }
    public void deletePr(Product product){
        this.product.remove(product);
    }



}
