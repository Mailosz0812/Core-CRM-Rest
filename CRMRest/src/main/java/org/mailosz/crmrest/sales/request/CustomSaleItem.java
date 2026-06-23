package org.mailosz.crmrest.sales.request;

import jakarta.validation.constraints.*;
import org.mailosz.crmrest.helpers.validator.TpsDate;
import org.mailosz.crmrest.prices.SellingUnit;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

public class CustomSaleItem {

    @NotBlank
    @Size(max=50)
    private String name;

    @NotBlank
    @Size(max=50)
    private String internal;

    @NotNull
    @DecimalMin(value = "0.00",message = "Price should be greater than or equal zero")
    private BigDecimal unitPrice;

    @NotNull
    private SellingUnit unit;

    @NotNull
    @Positive
    private BigDecimal amount;

    @NotNull
    @TpsDate
    private OffsetDateTime tps;

    @Size(max=30)
    private String pack;

    public CustomSaleItem(String name, String internal, BigDecimal unitPrice, SellingUnit unit, BigDecimal amount, OffsetDateTime tps, String pack) {
        this.name = name;
        this.internal = internal;
        this.unitPrice = unitPrice;
        this.unit = unit;
        this.amount = amount;
        this.tps = tps;
        this.pack = pack;
    }

    public CustomSaleItem() {
    }

    public String getName() {
        return name;
    }


    public BigDecimal getUnitPrice() {
        return unitPrice;
    }


    public SellingUnit getUnit() {
        return unit;
    }


    public BigDecimal getAmount() {
        return amount;
    }

    public @NotBlank String getInternal() {
        return internal;
    }

    public OffsetDateTime getTps() {
        return tps;
    }

    public String getPack() {
        return pack;
    }
}
