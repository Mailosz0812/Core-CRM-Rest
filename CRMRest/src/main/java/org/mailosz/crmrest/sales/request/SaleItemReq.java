package org.mailosz.crmrest.sales.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;
import java.util.UUID;

public class SaleItemReq {

    @NotNull
    private UUID prodId;

    @NotNull
    @Positive
    private BigDecimal amount;

    public SaleItemReq() {
    }

    public SaleItemReq(UUID prodId, BigDecimal amount) {
        this.prodId = prodId;
        this.amount = amount;
    }

    public @NotNull UUID getProdId() {
        return prodId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

}
