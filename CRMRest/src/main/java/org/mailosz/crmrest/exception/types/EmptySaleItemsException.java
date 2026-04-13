package org.mailosz.crmrest.exception.types;

import org.springframework.http.HttpStatus;

public class EmptySaleItemsException extends BusinessRuleException {
    public EmptySaleItemsException(String message) {
        super(message,"EMPTY_SALE_ITEMS", HttpStatus.UNPROCESSABLE_CONTENT);
    }
}
