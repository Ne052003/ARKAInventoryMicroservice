package com.neoapps.entry_points.exceptions;

import com.neoapps.driven_adapters.exceptions.RepositoryException;
import com.neoapps.exceptions.DomainException;
import com.neoapps.exceptions.ProductAlreadyExistsException;
import com.neoapps.exceptions.ProductNotFoundException;
import com.neoapps.exceptions.StockCannotBeNegativeException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalHandlerException {

    @ExceptionHandler(DomainException.class)
    public ResponseEntity<ErrorResponse> handleDomainException(DomainException ex) {
        ErrorResponse errorResponse = new ErrorResponse("DOMAIN_ERROR", ex.getField() + ". " + ex.getMessage());
        return ResponseEntity.badRequest().body(errorResponse);
    }

    @ExceptionHandler(RepositoryException.class)
    public ResponseEntity<ErrorResponse> handlerRepositoryException(RepositoryException ex) {
        ErrorResponse errorResponse = new ErrorResponse("PERSISTENCE_ERROR", ex.getField() + ". " + ex.getMessage());
        return ResponseEntity.internalServerError().body(errorResponse);
    }

    @ExceptionHandler(ProductAlreadyExistsException.class)
    public ResponseEntity<ErrorResponse> handlerProductAlreadyExistsException(ProductAlreadyExistsException ex) {
        ErrorResponse errorResponse = new ErrorResponse("PRODUCT_NAME_ERROR", ex.getMessage());
        return ResponseEntity.badRequest().body(errorResponse);
    }

    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<ErrorResponse> handlerProductNotFoundException(ProductNotFoundException ex) {
        ErrorResponse errorResponse = new ErrorResponse("PRODUCT_NOT_FOUND_ERROR", ex.getMessage());
        return ResponseEntity.status(404).body(errorResponse);
    }

    @ExceptionHandler(StockCannotBeNegativeException.class)
    public ResponseEntity<ErrorResponse> handlerStockCannotBeNegativeException(StockCannotBeNegativeException ex) {
        ErrorResponse errorResponse = new ErrorResponse("PRODUCT_STOCK_ERROR", ex.getMessage());
        return ResponseEntity.badRequest().body(errorResponse);
    }
}
