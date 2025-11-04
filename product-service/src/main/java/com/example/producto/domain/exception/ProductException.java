package com.example.producto.domain.exception;

public class ProductException extends RuntimeException {
    public ProductException(String menses) {
        super(menses);
    }
}
