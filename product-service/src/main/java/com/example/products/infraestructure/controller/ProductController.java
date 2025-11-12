package com.example.products.infraestructure.controller;


import com.example.products.application.ProductsService;
import com.example.products.application.dto.ProductRequest;
import com.example.products.application.dto.ProductResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@SuppressWarnings("unused")
@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/products")
public class ProductController implements ProductAPI {

    private final ProductsService service;

    @Override
    @GetMapping("/{productId}/brands/{brandId}/price")
    public ResponseEntity<ProductResponse> getProductPrice(
            @PathVariable("productId") Integer productId,
            @PathVariable("brandId") Integer brandId,
            @RequestParam("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime date
    ) {
        ProductRequest request = new ProductRequest(date, productId, brandId);
        ProductResponse response = service.getApplicablePrice(request);

        return ResponseEntity.ok(response);
    }
}
