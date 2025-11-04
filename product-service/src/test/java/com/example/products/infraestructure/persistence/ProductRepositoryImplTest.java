package com.example.products.infraestructure.persistence;

import com.example.products.application.dto.ProductRequest;
import com.example.products.domain.model.Product;
import com.example.products.infraestructure.persistence.entity.ProductEntity;
import com.example.products.infraestructure.persistence.mapper.ProductMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;

class ProductRepositoryImplTest {

    @Test
    void testFindApplicablePrice_Success() {
        // Arrange
        ProductJpaRepository mockJpa = Mockito.mock(ProductJpaRepository.class);
        ProductMapper mockMapper = Mockito.mock(ProductMapper.class);
        ProductRepositoryImpl repository = new ProductRepositoryImpl(mockJpa,mockMapper);

        ProductEntity product = new ProductEntity();
        product.setProductId(35455);
        product.setBrandId(1);
        product.setPriceList(1);
        product.setStartDate(LocalDateTime.parse("2020-06-14T00:00:00"));
        product.setEndDate(LocalDateTime.parse("2020-06-14T23:59:59"));
        product.setPrice(35.50);
        product.setCurrency("EUR");

        Mockito.when(mockJpa.findByProductIdBrandIdAndDate(anyInt(), anyInt(), any(LocalDateTime.class)))
                .thenReturn(Optional.of(product));


        Mockito.when(mockMapper.toDomain(product))
                .thenReturn(Product.builder()
                        .productId(product.getProductId())
                        .brandId(product.getBrandId())
                        .priceList(product.getPriceList())
                        .startDate(product.getStartDate())
                        .endDate(product.getEndDate())
                        .price(product.getPrice())
                        .currency(product.getCurrency())
                        .build());

        ProductRequest request = new ProductRequest(LocalDateTime.parse("2020-06-14T10:00:00"),35455, 1);

        // Act
        Optional<Product> response = repository.findApplicablePrice(
                request.productId(),
                request.brandId(),
                request.date()
        );

        // Assert

        assertTrue(response.isPresent());
        assertEquals(35455, response.get().getProductId());
        assertEquals(1, response.get().getBrandId());
        assertEquals(35.50, response.get().getPrice());
        assertEquals("EUR", response.get().getCurrency());

    }

    @Test
    void testFindApplicablePrice_NotFound() {
        // Arrange
        ProductJpaRepository mockJpa = Mockito.mock(ProductJpaRepository.class);
        ProductMapper mockMapper = Mockito.mock(ProductMapper.class);

        ProductRepositoryImpl repository = new ProductRepositoryImpl(mockJpa, mockMapper);

        Mockito.when(mockJpa.findByProductIdBrandIdAndDate(anyInt(), anyInt(), any(LocalDateTime.class)))
                .thenReturn(Optional.empty());

        Optional<Product> result = repository.findApplicablePrice(99999, 1, LocalDateTime.parse("2020-06-14T10:00:00"));

        assertTrue(result.isEmpty());

    }
}
