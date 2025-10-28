package com.example.producto.domain.repository;


import com.example.producto.application.dto.ProductoRequestDTO;
import com.example.producto.application.dto.ProductoResponseDTO;
import java.util.Optional;

public interface ProductoRepository {
    ProductoResponseDTO findApplicablePrice(ProductoRequestDTO request);
}