package com.example.products.infraestructure.controller;

import com.example.products.application.dto.ProductResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;

@Tag(name = "Products API", description = "Product Status")
public interface ProductAPI {

    @Operation(summary = "Check prices", description = "Check prices applicable according to date, product, and chain")
    @ApiResponse(responseCode = "200", description = "Check Correct", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ProductResponse.class)))
    @ApiResponse(responseCode = "404", description = "Product Not Found", content = @Content)
    ResponseEntity<ProductResponse> getProductPrice(Integer productId, Integer brandId, LocalDateTime date);
}
