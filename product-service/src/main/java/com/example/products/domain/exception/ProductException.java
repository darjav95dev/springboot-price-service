package com.example.products.domain.exception;

public class ProductException extends RuntimeException {
    public ProductException(String menses) {
        super(menses);
    }
}
