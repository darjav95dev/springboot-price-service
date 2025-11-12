package com.example.products.infraestructure.handler;

import com.example.products.domain.exception.ProductException;
import com.example.products.infraestructure.controller.ApiErrorResponse;
import java.time.LocalDateTime;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/** The type Global exception handler. */
@SuppressWarnings("unused")
@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

  /**
   * Handle product exception response entity.
   *
   * @param ex the ex
   * @return the response entity
   */
  @ExceptionHandler(ProductException.class)
  public ResponseEntity<ApiErrorResponse> handleProductException(ProductException ex) {
    ApiErrorResponse error =
        new ApiErrorResponse(
            LocalDateTime.now(),
            HttpStatus.NOT_FOUND.value(),
            "Product Not Found",
            ex.getMessage());
    log.error("ProductException: {}", ex.getMessage(), ex);
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
  }
}
