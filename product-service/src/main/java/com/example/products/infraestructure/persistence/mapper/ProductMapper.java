package com.example.products.infraestructure.persistence.mapper;

import com.example.products.domain.model.Product;
import com.example.products.infraestructure.persistence.entity.ProductEntity;
import org.springframework.stereotype.Component;

/** The type Product mapper. */
@Component
public class ProductMapper {

  /**
   * To domain product.
   *
   * @param entity the entity
   * @return the product
   */
  public Product toDomain(ProductEntity entity) {
    return Product.builder()
        .id(entity.getId())
        .brandId(entity.getBrandId())
        .startDate(entity.getStartDate())
        .endDate(entity.getEndDate())
        .priceList(entity.getPriceList())
        .productId(entity.getProductId())
        .priority(entity.getPriority())
        .price(entity.getPrice())
        .currency(entity.getCurrency())
        .build();
  }

  /**
   * To entity product entity.
   *
   * @param domain the domain
   * @return the product entity
   */
  public ProductEntity toEntity(Product domain) {
    return ProductEntity.builder()
        .id(domain.getId())
        .brandId(domain.getBrandId())
        .startDate(domain.getStartDate())
        .endDate(domain.getEndDate())
        .priceList(domain.getPriceList())
        .productId(domain.getProductId())
        .priority(domain.getPriority())
        .price(domain.getPrice())
        .currency(domain.getCurrency())
        .build();
  }
}
