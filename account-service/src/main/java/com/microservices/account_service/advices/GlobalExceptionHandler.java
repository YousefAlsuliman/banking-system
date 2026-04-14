package com.microservices.account_service.advices;

import com.microservices.account_service.exceptions.NoAccountsFoundException;
import com.microservices.account_service.models.errors.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(NoAccountsFoundException.class)
    public ResponseEntity<ErrorResponse> handleNoAccountsFound(NoAccountsFoundException exception){
        ErrorResponse errorResponse= new ErrorResponse(exception.getMessage(), exception.getCode());
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }
}
