package org.mailosz.crmrest.exception.types;

import org.springframework.http.HttpStatus;

public class InvalidSaleDateException extends BusinessRuleException {
    public InvalidSaleDateException(String message) {
        super(message, "INVALID_SALE_DATE",HttpStatus.UNPROCESSABLE_CONTENT);
    }
}
