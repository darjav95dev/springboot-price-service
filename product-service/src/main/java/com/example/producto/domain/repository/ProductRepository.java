package com.example.producto.domain.repository;


import com.example.producto.application.dto.ProductRequest;
import com.example.producto.application.dto.ProductResponse;

public interface ProductRepository {
    ProductResponse findApplicablePrice(ProductRequest request);
}