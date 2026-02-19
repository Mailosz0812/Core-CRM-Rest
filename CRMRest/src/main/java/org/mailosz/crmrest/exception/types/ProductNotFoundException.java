package org.mailosz.crmrest.exception.types;

import java.util.Map;

public class ProductNotFoundException extends EntityNotFoundException {
    public ProductNotFoundException(String prodId,String errorCode) {
        super(
                String.format("Product with id: %s not found in price list",prodId),
                errorCode,
                Map.of("Id", prodId)
        );
    }
}
