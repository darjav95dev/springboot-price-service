package com.example.producto.application.dto;

import java.time.LocalDateTime;

public record ProductoRequest(LocalDateTime date, Integer productId, Integer brandId) {
}

