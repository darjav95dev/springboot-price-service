package com.example.producto.application.dto;

import java.time.LocalDateTime;

public record ProductoResponse(Integer productId, Integer brandId, Integer priceList, LocalDateTime startDate, LocalDateTime endDate, double price, String currency) {
}
