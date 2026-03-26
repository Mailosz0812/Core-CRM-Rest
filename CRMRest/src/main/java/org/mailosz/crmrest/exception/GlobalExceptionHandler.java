package org.mailosz.crmrest.exception;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import org.mailosz.crmrest.exception.types.CRMBaseException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

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
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<ErrorResponse> handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException ex){
        ErrorResponse err = new ErrorResponse(
                "Method argument type mismatch",
                "ARGUMENT_TYPE_MISMATCH");
        return new ResponseEntity<>(err,HttpStatus.BAD_REQUEST);
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
    @ExceptionHandler(MalformedJwtException.class)
    public ResponseEntity<ErrorResponse> handleMalformedException(MalformedJwtException ex){
        ErrorResponse errResp = new ErrorResponse(
                "Malformed authentication token",
                "JWT_MALFORMED"
        );
        return new ResponseEntity<>(errResp,HttpStatus.valueOf(401));
    }

    @ExceptionHandler(ExpiredJwtException.class)
    public ResponseEntity<ErrorResponse> handleExpiredJwtException(ExpiredJwtException ex){
        ErrorResponse errResp = new ErrorResponse(
                "Expired authentication token",
                "JWT_EXPIRED"
        );
        return new ResponseEntity<>(errResp,HttpStatus.valueOf(401));
    }
    @ExceptionHandler(UnsupportedJwtException.class)
    public ResponseEntity<ErrorResponse> handleUnsupportedJwtException(UnsupportedJwtException ex){
        ErrorResponse errResp = new ErrorResponse(
                "Unsupported authentication token",
                "JWT_UNSUPPORTED"
        );
        return new ResponseEntity<>(errResp,HttpStatus.valueOf(401));
    }
    @ExceptionHandler(JwtException.class)
    public ResponseEntity<ErrorResponse> handleJwtException(JwtException ex){
        ErrorResponse errResp = new ErrorResponse(
                "Jwt error",
                "JWT_ERROR"
        );
        return new ResponseEntity<>(errResp,HttpStatus.valueOf(401));
    }
    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<ErrorResponse> handleBadCredentialException(BadCredentialsException ex){
        ErrorResponse err = new ErrorResponse(
                "Authentication error, bad credentials",
                "BAD_CREDENTIALS"
        );
        return new ResponseEntity<>(err,HttpStatus.UNAUTHORIZED);
    }
}
