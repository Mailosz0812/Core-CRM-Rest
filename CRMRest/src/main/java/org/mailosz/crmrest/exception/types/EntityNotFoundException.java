package org.mailosz.crmrest.exception.types;

import org.springframework.http.HttpStatus;

import java.util.Map;

public class EntityNotFoundException extends CRMBaseException {
    public EntityNotFoundException(String message, String errorCode, Map<String,Object> metadata) {
        super(message,errorCode,HttpStatus.NOT_FOUND,metadata);
    }
    public EntityNotFoundException(String message, String errorCode){
        super(message,errorCode,HttpStatus.NOT_FOUND);
    }
}
