package com.example.compbuy;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.XADataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

import java.sql.SQLException;


@SpringBootApplication
public class CompBuyApplication {

    public static void main(String[] args) throws SQLException {
        SpringApplication.run(CompBuyApplication.class, args);

    }

}
