package com.example.producto.domain.repository;


import com.example.producto.application.dto.ProductoRequest;
import com.example.producto.application.dto.ProductoResponse;

public interface ProductoRepository {
    ProductoResponse findApplicablePrice(ProductoRequest request);
}