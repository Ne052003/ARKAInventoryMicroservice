package com.neoapps.exceptions;

public class ProductAlreadyExistsException extends RuntimeException {

    public ProductAlreadyExistsException(String productName) {
        super("A product named: " + productName + " already exists");
    }
}
