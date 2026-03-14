package org.mailosz.crmrest.exception.types;

import org.springframework.http.HttpStatus;

import java.util.Map;

public class ProductOutOfStockException extends BusinessRuleException {
    public ProductOutOfStockException(String message, String errCode, Map<String,Object> metadata) {
        super(message,errCode, HttpStatus.UNPROCESSABLE_CONTENT,metadata);
    }
}
