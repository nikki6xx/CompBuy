package com.example.compbuy.model;

import javax.persistence.*;
import java.io.Serializable;

    @Entity
    @Table(name = "Order_Details")
    public class OrderDetail {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private String id;

        @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
        @JoinColumn(name = "orders_id")
        private Order order;

        @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
        @JoinColumn(name = "products_id")
        private Product product;
        private double price;
        private double amount;
}
