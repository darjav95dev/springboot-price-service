package com.example.products.application;

import com.example.products.application.dto.ProductRequest;
import com.example.products.application.dto.ProductResponse;
import com.example.products.domain.exception.ProductException;
import com.example.products.domain.model.Product;
import com.example.products.domain.repository.ProductRepository;
import org.springframework.stereotype.Service;

@Service
public class ProductsService {

    private final ProductRepository repository;

    public ProductsService(ProductRepository repository) {
        this.repository = repository;
    }

    public ProductResponse getApplicablePrice(ProductRequest request) {
        Product product = repository
                .findApplicablePrice(
                        request.productId(),
                        request.brandId(),
                        request.date()
                )
                .orElseThrow(() -> new ProductException(
                        String.format(
                                "No applicable price found for product %d from brand %d on date %s",
                                request.productId(),
                                request.brandId(),
                                request.date()
                        )
                ));

        return mapToResponse(product);
    }

    private ProductResponse mapToResponse(Product product) {
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