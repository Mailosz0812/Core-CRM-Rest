package org.mailosz.crmrest.exception.types;

import java.util.Map;

public class CrmUserNotFoundException extends EntityNotFoundException {
  public CrmUserNotFoundException(String userId,String errCode) {
    super(String.format("User with id: %s not found",userId)
    ,errCode, Map.of("Id",userId));
  }
}
