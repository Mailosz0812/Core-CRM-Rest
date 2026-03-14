package org.mailosz.crmrest.exception.types;

import org.springframework.http.HttpStatus;

import java.util.Map;

public class EntityAlreadyExists extends CRMBaseException {
    public EntityAlreadyExists(String message, String errCode) {
        super(
                message,errCode, HttpStatus.CONFLICT
        );
    }
}
