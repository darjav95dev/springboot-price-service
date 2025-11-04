package com.example.products.domain.repository;


import com.example.products.application.dto.ProductRequest;
import com.example.products.application.dto.ProductResponse;

public interface ProductRepository {
    ProductResponse findApplicablePrice(ProductRequest request);
}