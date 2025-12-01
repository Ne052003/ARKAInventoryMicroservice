package com.neoapps.exceptions;

public class StockCannotBeNegativeException extends RuntimeException {
    public StockCannotBeNegativeException(Integer stockEntered) {
        super("The stock of a product can't be negative. Invalid value: " + stockEntered);
    }
}
