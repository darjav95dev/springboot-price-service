package com.example.products.infraestructure.config;


import com.example.products.infraestructure.persistence.ProductJpaRepository;
import com.example.products.infraestructure.persistence.entity.ProductEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {

    private final ProductJpaRepository repository;

    @Override
    public void run(String... args) {
        List<ProductEntity> products = List.of(
                ProductEntity.builder()
                        .brandId(1)
                        .startDate(LocalDateTime.of(2020, 6, 14, 0, 0))
                        .endDate(LocalDateTime.of(2020, 12, 31, 23, 59, 59))
                        .priceList(1)
                        .productId(35455)
                        .priority(0)
                        .price(35.50)
                        .currency("EUR")
                        .build(),
                ProductEntity.builder()
                        .brandId(1)
                        .startDate(LocalDateTime.of(2020, 6, 14, 15, 0))
                        .endDate(LocalDateTime.of(2020, 6, 14, 18, 30))
                        .priceList(2)
                        .productId(35455)
                        .priority(1)
                        .price(25.45)
                        .currency("EUR")
                        .build(),
                ProductEntity.builder()
                        .brandId(1)
                        .startDate(LocalDateTime.of(2020, 6, 15, 0, 0))
                        .endDate(LocalDateTime.of(2020, 6, 15, 11, 0))
                        .priceList(3)
                        .productId(35455)
                        .priority(1)
                        .price(30.50)
                        .currency("EUR")
                        .build(),
                ProductEntity.builder()
                        .brandId(1)
                        .startDate(LocalDateTime.of(2020, 6, 15, 16, 0))
                        .endDate(LocalDateTime.of(2020, 12, 31, 23, 59, 59))
                        .priceList(4)
                        .productId(35455)
                        .priority(1)
                        .price(38.95)
                        .currency("EUR")
                        .build()
        );

        repository.saveAll(products);
    }
}

