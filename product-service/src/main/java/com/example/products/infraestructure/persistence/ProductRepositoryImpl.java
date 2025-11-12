package com.example.products.infraestructure.persistence;


import com.example.products.domain.model.Product;
import com.example.products.domain.repository.ProductRepository;
import com.example.products.infraestructure.persistence.mapper.ProductMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Optional;


@Repository
@RequiredArgsConstructor
public class ProductRepositoryImpl implements ProductRepository {

    private final ProductJpaRepository jpaRepository;
    private final ProductMapper mapper;


    @Override
    public Optional<Product> findApplicablePrice(
            Integer productId,
            Integer brandId,
            LocalDateTime date
    ) {

        return jpaRepository
                .findByProductIdBrandIdAndDate(productId, brandId, date)
                .map(mapper::toDomain);
    }
}
