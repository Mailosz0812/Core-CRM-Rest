package org.mailosz.crmrest.exception.types;

import org.springframework.http.HttpStatus;

public class UnprocessableContent extends CRMBaseException {
    public UnprocessableContent(String message,String errCode) {
        super(message,errCode, HttpStatus.UNPROCESSABLE_CONTENT);
    }
}
