package com.example.products.config;


import com.example.products.domain.model.Products;
import com.example.products.infraestructure.persistence.ProductJpaRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
public class DataInitializer implements CommandLineRunner {
    private final ProductJpaRepository repository;

    public DataInitializer(ProductJpaRepository repository) {
        this.repository = repository;
    }


    @Override
    public void run(String... args) {
        repository.saveAll(List.of(
                new Products(null, 1, LocalDateTime.of(2020, 6, 14, 0, 0), LocalDateTime.of(2020, 12, 31, 23, 59, 59), 1, 35455, 0, 35.50, "EUR"),
                new Products(null, 1, LocalDateTime.of(2020, 6, 14, 15, 0), LocalDateTime.of(2020, 6, 14, 18, 30), 2, 35455, 1, 25.45, "EUR"),
                new Products(null, 1, LocalDateTime.of(2020, 6, 15, 0, 0), LocalDateTime.of(2020, 6, 15, 11, 0), 3, 35455, 1, 30.50, "EUR"),
                new Products(null, 1, LocalDateTime.of(2020, 6, 15, 16, 0), LocalDateTime.of(2020, 12, 31, 23, 59, 59), 4, 35455, 1, 38.95, "EUR")
        ));

    }
}

