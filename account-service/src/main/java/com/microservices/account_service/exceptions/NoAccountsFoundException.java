package com.microservices.account_service.exceptions;

import com.microservices.account_service.enums.ErrorCode;

public class NoAccountsFoundException extends GenericException{

    public NoAccountsFoundException(){
        super(ErrorCode.NO_ACCOUNT_FOUND.getMessageKey(), ErrorCode.NO_ACCOUNT_FOUND.getCode());
    }
}
