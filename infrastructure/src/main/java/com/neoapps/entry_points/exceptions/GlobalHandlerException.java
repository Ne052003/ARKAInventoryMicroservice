package com.neoapps.entry_points.exceptions;

import com.neoapps.driven_adapters.exceptions.RepositoryException;
import com.neoapps.exceptions.DomainException;
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
}
