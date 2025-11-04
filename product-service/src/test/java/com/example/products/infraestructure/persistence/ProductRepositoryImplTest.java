package com.example.products.infraestructure.persistence;

import com.example.products.application.dto.ProductRequest;
import com.example.products.application.dto.ProductResponse;
import com.example.products.domain.exception.ProductException;
import com.example.products.domain.model.Products;
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
        ProductRepositoryImpl repository = new ProductRepositoryImpl(mockJpa);

        Products product = new Products();
        product.setProductId(35455);
        product.setBrandId(1);
        product.setPriceList(1);
        product.setStartDate(LocalDateTime.parse("2020-06-14T00:00:00"));
        product.setEndDate(LocalDateTime.parse("2020-06-14T23:59:59"));
        product.setPrice(35.50);
        product.setCurrency("EUR");

        Mockito.when(mockJpa.findByProductBrandAndDate(anyInt(), anyInt(), any(LocalDateTime.class)))
                .thenReturn(Optional.of(product));

        ProductRequest request = new ProductRequest(LocalDateTime.parse("2020-06-14T10:00:00"),35455, 1);

        // Act
        ProductResponse response = repository.findApplicablePrice(request);

        // Assert
        assertEquals(35455, response.productId());
        assertEquals(1, response.brandId());
        assertEquals(35.50, response.price());
        assertEquals("EUR", response.currency());
    }

    @Test
    void testFindApplicablePrice_NotFound() {
        // Arrange
        ProductJpaRepository mockJpa = Mockito.mock(ProductJpaRepository.class);
        ProductRepositoryImpl repository = new ProductRepositoryImpl(mockJpa);

        Mockito.when(mockJpa.findByProductBrandAndDate(anyInt(), anyInt(), any(LocalDateTime.class)))
                .thenReturn(Optional.empty());

        ProductRequest request = new ProductRequest(LocalDateTime.parse("2020-06-14T10:00:00"),99999, 1);

        // Act & Assert
        assertThrows(ProductException.class, () -> repository.findApplicablePrice(request));
    }
}
