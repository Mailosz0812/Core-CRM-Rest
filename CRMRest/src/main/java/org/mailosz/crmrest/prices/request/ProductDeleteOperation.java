package org.mailosz.crmrest.prices.request;

import jakarta.validation.constraints.NotNull;
import org.mailosz.crmrest.prices.OperationType;
import org.mailosz.crmrest.prices.ProductOperation;

import java.util.UUID;

public class ProductDeleteOperation extends ProductOperation {

    @NotNull
    private UUID id;

    public ProductDeleteOperation(OperationType operationType, UUID id) {
        super(operationType);
        this.id = id;
    }

    public UUID getId() {
        return id;
    }
}

