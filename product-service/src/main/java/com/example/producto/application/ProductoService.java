package com.example.producto.application;

import com.example.producto.application.dto.ProductoRequest;
import com.example.producto.application.dto.ProductoResponse;
import com.example.producto.domain.repository.ProductoRepository;
import org.springframework.stereotype.Service;

@Service
public class ProductoService {

    private final ProductoRepository repository;

    public ProductoService(ProductoRepository repository) {
        this.repository = repository;
    }

    public ProductoResponse getApplicablePrice(ProductoRequest request) {
        return repository.findApplicablePrice(request);
    }
}

