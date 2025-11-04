package com.example.producto.infraestructure.persistence;


import com.example.producto.application.dto.ProductRequest;
import com.example.producto.application.dto.ProductResponse;
import com.example.producto.domain.exception.ProductException;
import com.example.producto.domain.model.Products;
import com.example.producto.domain.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;


@Repository
@RequiredArgsConstructor
public class ProductRepositoryImpl implements ProductRepository {

    private final ProductJpaRepository jpaRepository;

    @Override
    public ProductResponse findApplicablePrice(ProductRequest request) {
        Products product = jpaRepository
                .findByProductBrandAndDate(
                        request.productId(),
                        request.brandId(),
                        request.date()
                )
                .orElseThrow(() -> new ProductException(
                        String.format("No applicable price found for product %d from brand %d on date %s",
                                request.productId(),
                                request.brandId(),
                                request.date()
                        )
                ));

        return mapToResponse(product);
    }

    private ProductResponse mapToResponse(Products product) {
        return new ProductResponse(
                product.getProductId(),
                product.getBrandId(),
                product.getPriceList(),
                product.getStartDate(),
                product.getEndDate(),
                product.getPrice(),
                product.getCurrency()
        );
    }
}
