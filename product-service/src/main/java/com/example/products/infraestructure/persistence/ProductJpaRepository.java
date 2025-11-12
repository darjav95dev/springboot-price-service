package com.example.products.infraestructure.persistence;

import com.example.products.infraestructure.persistence.entity.ProductEntity;
import java.time.LocalDateTime;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/** The interface Product jpa repository. */
public interface ProductJpaRepository extends JpaRepository<ProductEntity, Long> {

  /**
   * Find by product id brand id and date optional.
   *
   * @param productId the product id
   * @param brandId the brand id
   * @param applicationDate the application date
   * @return the optional
   */
  @Query(
      """
            SELECT p FROM ProductEntity p
            WHERE p.productId = :productId
              AND p.brandId = :brandId
              AND p.startDate <= :applicationDate
              AND p.endDate >= :applicationDate
            ORDER BY p.priority DESC
            LIMIT 1
            """)
  Optional<ProductEntity> findByProductIdBrandIdAndDate(
      @Param("productId") Integer productId,
      @Param("brandId") Integer brandId,
      @Param("applicationDate") LocalDateTime applicationDate);
}
