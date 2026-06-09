package org.mailosz.crmrest.exception.types;

import org.springframework.http.HttpStatus;

public class InsufficientPrivilegesException extends CRMBaseException {
    public InsufficientPrivilegesException(String message) {
        super(message,"INVALID_PRIVILEGES", HttpStatus.UNAUTHORIZED);
    }
}
