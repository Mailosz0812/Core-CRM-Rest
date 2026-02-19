package org.mailosz.crmrest.exception;

import org.mailosz.crmrest.exception.types.CRMBaseException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(CRMBaseException.class)
    public ResponseEntity<ErrorResponse> handleCRMBaseException(CRMBaseException ex){
        ErrorResponse errResp = new ErrorResponse(
                ex.getMessage(),
                ex.getErrorCode(),
                ex.getMetadata()
        );
        return new ResponseEntity<>(errResp,ex.getStatus());
    }
}
