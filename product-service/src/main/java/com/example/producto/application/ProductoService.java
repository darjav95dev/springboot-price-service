package com.example.producto.application;

import com.example.producto.application.dto.ProductoRequestDTO;
import com.example.producto.application.dto.ProductoResponseDTO;
import com.example.producto.domain.repository.ProductoRepository;
import com.example.producto.infraestructure.persistence.ProductoJpaRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductoService {

    private final ProductoRepository repository;

    public ProductoService(ProductoRepository repository) {
        this.repository = repository;
    }

    public ProductoResponseDTO getApplicablePrice(ProductoRequestDTO request) {
        return repository.findApplicablePrice(request);
    }
}

