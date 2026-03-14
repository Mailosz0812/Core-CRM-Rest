package org.mailosz.crmrest.exception.types;

import org.springframework.http.HttpStatus;

import java.util.Map;

public class BusinessRuleException extends CRMBaseException {
    public BusinessRuleException(String message, String errCode, HttpStatus status, Map<String,Object> metadata) {
        super(message,errCode,status,metadata);
    }
}
