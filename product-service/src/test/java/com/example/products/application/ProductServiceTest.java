package com.example.products.application;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;

import com.example.products.application.dto.ProductRequest;
import com.example.products.application.dto.ProductResponse;
import com.example.products.domain.model.Product;
import com.example.products.domain.repository.ProductRepository;
import java.time.LocalDateTime;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

/** The type Product service test. */
class ProductServiceTest {

  /** Test get applicable price. */
  @Test
  void testGetApplicablePrice() {
    // Arrange

    Product product = new Product();
    product.setProductId(35455);
    product.setBrandId(1);
    product.setPriceList(1);
    product.setStartDate(LocalDateTime.parse("2020-06-14T00:00:00"));
    product.setEndDate(LocalDateTime.parse("2020-06-14T23:59:59"));
    product.setPrice(35.50);
    product.setCurrency("EUR");

    ProductRepository mockRepository = Mockito.mock(ProductRepository.class);

    Mockito.when(mockRepository.findApplicablePrice(anyInt(), anyInt(), any(LocalDateTime.class)))
        .thenReturn(Optional.of(product));

    ProductRequest request =
        new ProductRequest(LocalDateTime.parse("2020-06-14T10:00:00"), 35455, 1);

    ProductsService service = new ProductsService(mockRepository);

    // Act
    ProductResponse actualResponse = service.getApplicablePrice(request);

    // Assert

    assertEquals(35455, actualResponse.productId());
    assertEquals(1, actualResponse.brandId());
    assertEquals(1, actualResponse.priceList());
    assertEquals(LocalDateTime.parse("2020-06-14T00:00:00"), actualResponse.startDate());
    assertEquals(LocalDateTime.parse("2020-06-14T23:59:59"), actualResponse.endDate());
    assertEquals(35.50, actualResponse.price());
    assertEquals("EUR", actualResponse.currency());
  }
}
