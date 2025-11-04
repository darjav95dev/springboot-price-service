package com.example.products.application.dto;

import java.time.LocalDateTime;

public record ProductResponse(Integer productId, Integer brandId, Integer priceList, LocalDateTime startDate,
                              LocalDateTime endDate, double price, String currency) {
}
