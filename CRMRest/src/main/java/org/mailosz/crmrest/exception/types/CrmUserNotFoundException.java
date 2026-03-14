package org.mailosz.crmrest.exception.types;

import java.util.Map;

public class CrmUserNotFoundException extends EntityNotFoundException {
  public CrmUserNotFoundException(String userId) {
    super(String.format("User with id: %s not found",userId)
    ,"USER_NOT_FOUND", Map.of("Id",userId));
  }
}
