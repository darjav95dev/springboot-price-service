package com.example.producto.application;

import com.example.producto.application.dto.ProductRequest;
import com.example.producto.application.dto.ProductResponse;
import com.example.producto.domain.repository.ProductRepository;
import org.springframework.stereotype.Service;

@Service
public class ProductsService {

    private final ProductRepository repository;

    public ProductsService(ProductRepository repository) {
        this.repository = repository;
    }

    public ProductResponse getApplicablePrice(ProductRequest request) {
        return repository.findApplicablePrice(request);
    }
}