package com.example.producto.infraestructure.persistence;


import com.example.producto.domain.model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface ProductoJpaRepository extends JpaRepository<Producto, Long> {

    Optional<Producto> findFirstByProductIdAndBrandIdAndStartDateLessThanEqualAndEndDateGreaterThanEqualOrderByPriorityDesc(
            Integer productId,
            Integer brandId,
            LocalDateTime date1,
            LocalDateTime date2
    );
}



