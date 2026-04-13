package org.mailosz.crmrest.exception.types;

import org.springframework.http.HttpStatus;

public class IllegalSaleOperation extends BusinessRuleException {
  public IllegalSaleOperation(String message) {
    super(message,"ILLEGAL_SALE_OPERATION", HttpStatus.UNPROCESSABLE_CONTENT);
  }
}
