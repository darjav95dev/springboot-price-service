package com.example.producto.application.dto;

import java.time.LocalDateTime;

public record ProductoRequestDTO (LocalDateTime date, Integer productId, Integer brandId) {
}

