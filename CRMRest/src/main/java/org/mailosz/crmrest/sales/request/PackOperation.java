package org.mailosz.crmrest.sales.request;

import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public class PackOperation {
    @NotNull
    private UUID saleId;

    public PackOperation(UUID saleId) {
        this.saleId = saleId;
    }

    public UUID getSaleId() {
        return saleId;
    }
}
