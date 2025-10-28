package com.example.producto.infraestructure.controller;

import com.example.producto.application.dto.ProductoResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;

@Tag(name = "Producto API", description = "Estado de Productos")
@RequestMapping("/api/productos")
public interface ProductoAPI {

    @GetMapping
    @Operation(summary = "Consulta de precios", description = "Consulta de precios aplicables según fecha, producto y cadena")
    @ApiResponse(responseCode = "200", description = "Consulta Correcta", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ProductoResponse.class)))
    @ApiResponse(responseCode = "404", description = "Producto no encontrado", content = @Content)
    ResponseEntity<?> getProductoPrice(
            @RequestParam("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime date,
            @RequestParam("productId") Integer productId,
            @RequestParam("brandId") Integer brandId
    );
}
