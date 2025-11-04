package com.example.products.domain.repository;


import com.example.products.domain.model.Product;

import java.time.LocalDateTime;
import java.util.Optional;

public interface ProductRepository {
    Optional<Product> findApplicablePrice(Integer productId,
                                          Integer brandId,
                                          LocalDateTime date);
}