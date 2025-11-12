package com.example.products.infraestructure.persistence.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.example.products.domain.model.Product;
import com.example.products.infraestructure.persistence.entity.ProductEntity;
import java.time.LocalDateTime;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

/** The type Product mapper test. */
@ExtendWith(MockitoExtension.class)
class ProductMapperTest {

  private final ProductMapper mapper = new ProductMapper();

  /** Test to domain. */
  @Test
  void testToDomain() {
    // Arrange
    ProductEntity entity =
        ProductEntity.builder()
            .id(100L)
            .brandId(1)
            .startDate(LocalDateTime.parse("2020-06-14T00:00:00"))
            .endDate(LocalDateTime.parse("2020-06-14T23:59:59"))
            .priceList(1)
            .productId(35455)
            .priority(0)
            .price(35.50)
            .currency("EUR")
            .build();

    // Act
    Product domain = mapper.toDomain(entity);

    // Assert
    assertEquals(100L, domain.getId());
    assertEquals(1, domain.getBrandId());
    assertEquals(LocalDateTime.parse("2020-06-14T00:00:00"), domain.getStartDate());
    assertEquals(LocalDateTime.parse("2020-06-14T23:59:59"), domain.getEndDate());
    assertEquals(1, domain.getPriceList());
    assertEquals(35455, domain.getProductId());
    assertEquals(0, domain.getPriority());
    assertEquals(35.50, domain.getPrice());
    assertEquals("EUR", domain.getCurrency());
  }

  /** Test to entity. */
  @Test
  void testToEntity() {
    // Arrange
    Product domain =
        Product.builder()
            .id(100L)
            .brandId(1)
            .startDate(LocalDateTime.parse("2020-06-14T00:00:00"))
            .endDate(LocalDateTime.parse("2020-06-14T23:59:59"))
            .priceList(1)
            .productId(35455)
            .priority(0)
            .price(35.50)
            .currency("EUR")
            .build();

    // Act
    ProductEntity entity = mapper.toEntity(domain);

    // Assert
    assertEquals(100L, entity.getId());
    assertEquals(1, entity.getBrandId());
    assertEquals(LocalDateTime.parse("2020-06-14T00:00:00"), entity.getStartDate());
    assertEquals(LocalDateTime.parse("2020-06-14T23:59:59"), entity.getEndDate());
    assertEquals(1, entity.getPriceList());
    assertEquals(35455, entity.getProductId());
    assertEquals(0, entity.getPriority());
    assertEquals(35.50, entity.getPrice());
    assertEquals("EUR", entity.getCurrency());
  }
}
