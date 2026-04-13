package org.mailosz.crmrest.exception.types;

import org.springframework.http.HttpStatus;

import java.util.Collection;
import java.util.Collections;
import java.util.Map;

public class BusinessRuleException extends CRMBaseException {
    public BusinessRuleException(String message, String errCode, HttpStatus status, Map<String,Object> metadata) {
        super(message,errCode,status,metadata);
    }
    public BusinessRuleException(String message, String errCode, HttpStatus status) {
        super(message,errCode,status);
    }
}
