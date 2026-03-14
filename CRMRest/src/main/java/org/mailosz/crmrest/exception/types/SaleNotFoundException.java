package org.mailosz.crmrest.exception.types;

import java.util.Map;

public class SaleNotFoundException extends EntityNotFoundException {
    public SaleNotFoundException(String saleId,String errCode) {
        super(
                String.format("Sale with id: %s not found",saleId),
                errCode,
                Map.of("id",saleId)
        );
    }
}
