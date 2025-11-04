package com.example.products.application.dto;

import java.time.LocalDateTime;

public record ProductRequest(LocalDateTime date, Integer productId, Integer brandId) {
}

