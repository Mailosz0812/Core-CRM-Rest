package org.mailosz.crmrest.prices.request;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import org.mailosz.crmrest.helpers.validator.TpsDate;
import org.mailosz.crmrest.prices.SellingUnit;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.UUID;

public class BaseItem {

    @NotNull
    private UUID prodId;

    @NotNull
    @DecimalMin(value = "0.00",message = "Price should be greater than or equal zero")
    private BigDecimal unitPrice;

    @NotNull
    private SellingUnit unit;

    @NotNull
    @TpsDate
    private OffsetDateTime tps;

    public BaseItem(UUID prodId, BigDecimal unitPrice, SellingUnit unit, OffsetDateTime tps) {
        this.prodId = prodId;
        this.unitPrice = unitPrice;
        this.unit = unit;
        this.tps = tps;
    }

    public @NotNull UUID getProdId() {
        return prodId;
    }

    public @NotNull @DecimalMin(value = "0.00", message = "Price should be greater than or equal zero") BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public @NotNull SellingUnit getUnit() {
        return unit;
    }

    public @NotNull OffsetDateTime getTps() {
        return tps;
    }
}
