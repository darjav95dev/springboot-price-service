package com.example.producto.infraestructure.persistence;


import com.example.producto.domain.model.Producto;
import com.example.producto.domain.repository.ProductoRepository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface ProductoJpaRepository extends JpaRepository<Producto, Long> {

    List<Producto> findByProductIdAndBrandIdAndStartDateLessThanEqualAndEndDateGreaterThanEqualOrderByPriorityDesc(
            Integer productId,
            Integer brandId,
            LocalDateTime date1,
            LocalDateTime date2
    );
}



