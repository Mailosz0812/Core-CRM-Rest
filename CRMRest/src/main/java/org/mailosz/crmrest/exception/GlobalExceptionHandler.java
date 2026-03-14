package org.mailosz.crmrest.exception;

import org.mailosz.crmrest.exception.types.CRMBaseException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

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

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, Object> errors = new HashMap<>();

        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = (error instanceof FieldError) ?
                    ((FieldError) error).getField() : error.getObjectName();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        ErrorResponse errResp = new ErrorResponse(
                "Validation Error",
                "VALIDATION_ERROR",
                errors
        );
        return new ResponseEntity<>(errResp,ex.getStatusCode());
    }
}
