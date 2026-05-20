package org.mailosz.crmrest.exception.types;

import org.springframework.http.HttpStatus;

public class EmptyPriceListItems extends BusinessRuleException{
    public EmptyPriceListItems(String message) {
        super(message,"EMPTY_SALE_ITEMS", HttpStatus.UNPROCESSABLE_CONTENT);
    }
}
