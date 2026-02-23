package org.mailosz.crmrest.exception.types;

public class SaleStageNotFoundException extends EntityNotFoundException {
    public SaleStageNotFoundException(String message, String errCode) {
        super(message,errCode);
    }
}
