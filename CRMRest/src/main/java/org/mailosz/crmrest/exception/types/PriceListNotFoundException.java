package org.mailosz.crmrest.exception.types;

import java.util.Map;

public class PriceListNotFoundException extends EntityNotFoundException {
    public PriceListNotFoundException(String listId,String errorCode) {
        super(
                String.format("Price list with id: %s not found",listId),
                errorCode,
                Map.of("Id",listId)
        );
    }
}
