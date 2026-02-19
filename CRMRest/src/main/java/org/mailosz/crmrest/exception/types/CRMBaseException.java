package org.mailosz.crmrest.exception.types;

import org.springframework.http.HttpStatus;

import java.util.Collections;
import java.util.Map;

public class CRMBaseException extends RuntimeException {
    private final String errorCode;
    private final HttpStatus status;
    private final Map<String,Object> metadata;
    protected CRMBaseException(String message, String errorCode, HttpStatus status, Map<String, Object> metadata) {
        super(message);
        this.errorCode = errorCode;
        this.status = status;
        this.metadata = metadata;
    }
    protected CRMBaseException(String message, String errorCode, HttpStatus httpStatus) {
        this(message, errorCode, httpStatus, Collections.emptyMap());
    }

}
