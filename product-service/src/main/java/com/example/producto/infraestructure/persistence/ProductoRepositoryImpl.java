package com.example.producto.infraestructure.persistence;


import com.example.producto.application.dto.ProductoRequest;
import com.example.producto.application.dto.ProductoResponse;
import com.example.producto.domain.exception.ProductoException;
import com.example.producto.domain.model.Producto;
import com.example.producto.domain.repository.ProductoRepository;
import org.springframework.stereotype.Repository;


@Repository
public class ProductoRepositoryImpl implements ProductoRepository {

    private final ProductoJpaRepository jpaRepository;

    public ProductoRepositoryImpl(ProductoJpaRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override

    public ProductoResponse findApplicablePrice(ProductoRequest request) {
        Producto producto = jpaRepository
                .findFirstByProductIdAndBrandIdAndStartDateLessThanEqualAndEndDateGreaterThanEqualOrderByPriorityDesc(
                        request.productId(),
                        request.brandId(),
                        request.date(),
                        request.date()
                )
                .orElseThrow(() -> new ProductoException(
                        "No se encontró precio aplicable para el producto " + request.productId() +
                                " con marca " + request.brandId() + " en la fecha " + request.date()
                ));

        return new ProductoResponse(
                producto.getProductId(),
                producto.getBrandId(),
                producto.getPriceList(),
                producto.getStartDate(),
                producto.getEndDate(),
                producto.getPrice(),
                producto.getCurrency()
        );
    }
}
