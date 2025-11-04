package com.example.products.infraestructure.controller;


import com.example.products.application.ProductsService;
import com.example.products.application.dto.ProductRequest;
import com.example.products.application.dto.ProductResponse;
import com.example.products.domain.exception.ProductException;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;


@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/products")
public class ProductController implements ProductAPI {

    private static final Logger log = LoggerFactory.getLogger(ProductController.class);
    private final ProductsService service;

    @Override
    @GetMapping("/{productId}/brands/{brandId}/price")
    public ResponseEntity<Object> getProductPrice(
            @PathVariable("productId") Integer productId,
            @PathVariable("brandId") Integer brandId,
            @RequestParam("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime date
    ) {

        ProductRequest request = new ProductRequest(date, productId, brandId);

        try {
            ProductResponse response = service.getApplicablePrice(request);
            return ResponseEntity.ok(response);
        } catch (ProductException ex) {
            log.error("Error getProductPrice: {}", ex.getMessage(), ex);

            ApiErrorResponse error = new ApiErrorResponse(
                    LocalDateTime.now(),
                    HttpStatus.NOT_FOUND.value(),
                    "Product Not Found",
                    ex.getMessage()
            );

            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
        }
    }
}
