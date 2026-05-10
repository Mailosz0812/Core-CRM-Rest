package org.mailosz.crmrest.exception.types;

import org.springframework.http.HttpStatus;

public class PDFGenerationException extends CRMBaseException {
    public PDFGenerationException(String message) {
        super(message,"PDF_GEN_EXCEPTION", HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
