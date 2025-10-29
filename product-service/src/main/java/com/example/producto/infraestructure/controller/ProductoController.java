package com.example.producto.infraestructure.controller;


import com.example.producto.application.ProductoService;
import com.example.producto.application.dto.ProductoRequest;
import com.example.producto.application.dto.ProductoResponse;
import com.example.producto.domain.exception.ProductoException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;


@Validated
@RestController
public class ProductoController implements ProductoAPI{

    private final ProductoService service;

    private static final Logger log = LoggerFactory.getLogger(ProductoController.class);
    public ProductoController(ProductoService service) {
        this.service = service;
    }

    @Override
    public ResponseEntity<?> getProductoPrice(
            @RequestParam("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime date,
            @RequestParam("productId") Integer productId,
            @RequestParam("brandId") Integer brandId
    ) {

        ProductoRequest request = new ProductoRequest(date, productId, brandId);

        try {
            ProductoResponse response = service.getApplicablePrice(request);
            return ResponseEntity.ok(response);
        } catch (ProductoException ex) {
            log.error("Error en getProductoPrice: {}", ex.getMessage(), ex);

            ApiErrorResponse error = new ApiErrorResponse(
                    LocalDateTime.now(),
                    HttpStatus.NOT_FOUND.value(),
                    "Producto no encontrado",
                    ex.getMessage()
            );

            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
        }
    }
}
