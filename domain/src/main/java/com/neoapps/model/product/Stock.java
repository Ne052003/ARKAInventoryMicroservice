package com.neoapps.model.product;

import com.neoapps.exceptions.DomainException;
import com.neoapps.exceptions.StockCannotBeNegativeException;

public record Stock(Integer value) {

    public Stock {
        validate(value);
    }


    public void validate(Integer value) {
        if (value == null) throw new DomainException("Can't be null", "Stock");
        if (value < 0) {
            throw new StockCannotBeNegativeException(value);
        }
    }
}
