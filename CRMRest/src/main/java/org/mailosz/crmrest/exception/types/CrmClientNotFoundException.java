package org.mailosz.crmrest.exception.types;

import java.util.Map;

public class CrmClientNotFoundException extends EntityNotFoundException {
    public CrmClientNotFoundException(String clientId,String errCode) {
        super(
                String.format("Client with id: %s not found",clientId),
                errCode,
                Map.of("Id",clientId)
        );
    }
}
