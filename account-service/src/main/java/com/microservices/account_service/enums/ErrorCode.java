package com.microservices.account_service.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ErrorCode {
    NO_ACCOUNT_FOUND("no.account.found", "ACT001");

    private final String messageKey;
    private final String code;

}
