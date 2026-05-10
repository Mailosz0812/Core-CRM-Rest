package org.mailosz.crmrest.sales.request;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.mailosz.crmrest.helpers.validator.TpsDate;
import org.mailosz.crmrest.prices.SellingUnit;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.UUID;

public class SaleItemReq {

    @NotNull
    private UUID prodId;

    @NotNull
    @Positive
    private BigDecimal amount;

    @NotNull
    @DecimalMin(value = "0.00",message = "Price should be greater than zero")
    private BigDecimal unitPrice;

    @NotNull
    private SellingUnit unit;

    @NotNull
    @TpsDate
    private OffsetDateTime tps;

    public SaleItemReq() {
    }

    public SaleItemReq(UUID prodId, BigDecimal amount, BigDecimal unitPrice, SellingUnit unit, OffsetDateTime tps) {
        this.prodId = prodId;
        this.amount = amount;
        this.unitPrice = unitPrice;
        this.unit = unit;
        this.tps = tps;
    }

    public @NotNull UUID getProdId() {
        return prodId;
    }

    public @NotNull @Positive BigDecimal getAmount() {
        return amount;
    }

    public @NotNull @DecimalMin(value = "0.00", message = "Price should be greater than zero") BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public @NotNull SellingUnit getUnit() {
        return unit;
    }

    public @NotNull OffsetDateTime getTps() {
        return tps;
    }

    public void setProdId(@NotNull UUID prodId) {
        this.prodId = prodId;
    }

    public void setAmount(@NotNull @Positive BigDecimal amount) {
        this.amount = amount;
    }

    public void setUnitPrice(@NotNull @DecimalMin(value = "0.00", message = "Price should be greater than zero") BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }

    public void setUnit(@NotNull SellingUnit unit) {
        this.unit = unit;
    }

    public void setTps(@NotNull OffsetDateTime tps) {
        this.tps = tps;
    }
}

