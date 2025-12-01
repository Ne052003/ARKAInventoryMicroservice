package com.neoapps.exceptions;

public class ProductNotFoundException extends RuntimeException {
    public ProductNotFoundException(Long productId) {
        super("A product with id " + productId + " does not exist");
    }
}
