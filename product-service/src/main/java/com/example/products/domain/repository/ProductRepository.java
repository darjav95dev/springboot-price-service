package com.example.products.domain.repository;

import com.example.products.domain.model.Product;
import java.time.LocalDateTime;
import java.util.Optional;

/** The interface Product repository. */
public interface ProductRepository {
  /**
   * Find applicable price optional.
   *
   * @param productId the product id
   * @param brandId the brand id
   * @param date the date
   * @return the optional
   */
  Optional<Product> findApplicablePrice(Integer productId, Integer brandId, LocalDateTime date);
}
