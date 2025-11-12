package com.example.products.application.dto;

import java.time.LocalDateTime;

/** The type Product request. */
public record ProductRequest(LocalDateTime date, Integer productId, Integer brandId) {}
