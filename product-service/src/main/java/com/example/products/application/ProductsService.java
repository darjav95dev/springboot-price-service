package com.example.products.application;

import com.example.products.application.dto.ProductRequest;
import com.example.products.application.dto.ProductResponse;
import com.example.products.domain.repository.ProductRepository;
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