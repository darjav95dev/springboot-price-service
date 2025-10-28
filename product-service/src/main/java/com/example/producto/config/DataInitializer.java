package com.example.producto.config;


import com.example.producto.domain.model.Producto;
import com.example.producto.infraestructure.persistence.ProductoJpaRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
public class DataInitializer implements CommandLineRunner {
    private final ProductoJpaRepository repository;

    public DataInitializer(ProductoJpaRepository repository) {
        this.repository = repository;
    }


    @Override
    public void run(String... args) {
        repository.saveAll(List.of(
                new Producto(null, 1, LocalDateTime.of(2020, 6, 14, 0, 0), LocalDateTime.of(2020, 12, 31, 23, 59, 59), 1, 35455, 0, 35.50, "EUR"),
                new Producto(null, 1, LocalDateTime.of(2020, 6, 14, 15, 0), LocalDateTime.of(2020, 6, 14, 18, 30), 2, 35455, 1, 25.45, "EUR"),
                new Producto(null, 1, LocalDateTime.of(2020, 6, 15, 0, 0), LocalDateTime.of(2020, 6, 15, 11, 0), 3, 35455, 1, 30.50, "EUR"),
                new Producto(null, 1, LocalDateTime.of(2020, 6, 15, 16, 0), LocalDateTime.of(2020, 12, 31, 23, 59, 59), 4, 35455, 1, 38.95, "EUR")
        ));

    }
}

