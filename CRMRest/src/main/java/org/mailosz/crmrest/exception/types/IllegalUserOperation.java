package org.mailosz.crmrest.exception.types;

import org.springframework.http.HttpStatus;

public class IllegalUserOperation extends BusinessRuleException {
    public IllegalUserOperation(String message) {
        super(message,"ILLEGAL_USER_OPERATION", HttpStatus.UNPROCESSABLE_CONTENT);
    }
}
