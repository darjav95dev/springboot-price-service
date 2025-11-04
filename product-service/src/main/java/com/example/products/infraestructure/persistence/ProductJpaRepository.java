package com.example.products.infraestructure.persistence;


import com.example.products.domain.model.Products;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.Optional;

public interface ProductJpaRepository extends JpaRepository<Products, Long> {

    @Query("""
    SELECT p FROM Products p
    WHERE p.productId = :productId
      AND p.brandId = :brandId
      AND p.startDate <= :applicationDate
      AND p.endDate >= :applicationDate
    ORDER BY p.priority DESC
    LIMIT 1
    """)
    Optional<Products> findByProductBrandAndDate(
            @Param("productId") Integer productId,
            @Param("brandId") Integer brandId,
            @Param("applicationDate") LocalDateTime applicationDate
    );
}



