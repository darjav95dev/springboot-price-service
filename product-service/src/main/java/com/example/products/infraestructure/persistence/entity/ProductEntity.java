package com.example.products.infraestructure.persistence.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/** The type Product entity. */
@Entity
@Table(
    name = "products",
    indexes = {
      @Index(
          name = "idx_product_brand_dates_priority",
          columnList = "product_id, brand_id, start_date, end_date, priority")
    })
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "brand_id", nullable = false)
  private Integer brandId;

  @Column(name = "start_date", nullable = false)
  private LocalDateTime startDate;

  @Column(name = "end_date", nullable = false)
  private LocalDateTime endDate;

  @Column(name = "price_list")
  private Integer priceList;

  @Column(name = "product_id", nullable = false)
  private Integer productId;

  @Column(name = "priority", nullable = false)
  private Integer priority;

  @Column(nullable = false)
  private Double price;

  @Column(length = 3)
  private String currency;
}
