package com.example.products.infraestructure.controller;

import com.example.products.application.ProductsService;
import com.example.products.application.dto.ProductRequest;
import com.example.products.application.dto.ProductResponse;
import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/** The type Product controller. */
@SuppressWarnings("unused")
@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/products")
public class ProductController implements ProductApi {

  private final ProductsService service;

  @Override
  @GetMapping("/{productId}/brands/{brandId}/price")
  public ResponseEntity<ProductResponse> getProductPrice(
      @PathVariable("productId") Integer productId,
      @PathVariable("brandId") Integer brandId,
      @RequestParam("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
          LocalDateTime date) {
    ProductRequest request = new ProductRequest(date, productId, brandId);
    ProductResponse response = service.getApplicablePrice(request);

    return ResponseEntity.ok(response);
  }
}
