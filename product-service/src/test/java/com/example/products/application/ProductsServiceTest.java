package com.example.products.application;

import com.example.products.application.dto.ProductRequest;
import com.example.products.application.dto.ProductResponse;
import com.example.products.domain.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import java.time.LocalDateTime;
import static org.junit.jupiter.api.Assertions.assertEquals;


class ProductsServiceTest {

    @Test
    void testGetApplicablePrice() {
        // Arrange
        ProductRepository mockRepository = Mockito.mock(ProductRepository.class);
        ProductsService service = new ProductsService(mockRepository);

        ProductResponse expectedResponse = new ProductResponse(
                35455, 1, 1,
                LocalDateTime.parse("2020-06-14T00:00:00"),
                LocalDateTime.parse("2020-06-14T23:59:59"),
                35.50, "EUR"
        );

        Mockito.when(mockRepository.findApplicablePrice(Mockito.any(ProductRequest.class)))
                .thenReturn(expectedResponse);

        ProductRequest request = new ProductRequest(
                LocalDateTime.parse("2020-06-14T10:00:00"),
                35455,
                1
        );

        // Act
        ProductResponse actualResponse = service.getApplicablePrice(request);

        // Assert
        assertEquals(expectedResponse, actualResponse);

    }

}
