package com.microservices.account_service.exceptions;

import lombok.Getter;

@Getter
public class GenericException extends RuntimeException{
    private final String message;
    private final String code;

    public GenericException(String message, String code){
        super(message);
        this.message = message;
        this.code = code;
    }

}
