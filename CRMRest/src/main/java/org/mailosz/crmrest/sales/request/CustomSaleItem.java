package org.mailosz.crmrest.sales.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.mailosz.crmrest.prices.SellingUnit;

import java.math.BigDecimal;

public class CustomSaleItem {

    @NotBlank
    private String name;

    @NotBlank
    private String internal;

    @NotNull
    @Positive
    private BigDecimal unitPrice;

    @NotNull
    private SellingUnit unit;

    @NotNull
    @Positive
    private BigDecimal amount;

    public CustomSaleItem(String name, String internal, BigDecimal unitPrice, SellingUnit unit, BigDecimal amount) {
        this.name = name;
        this.internal = internal;
        this.unitPrice = unitPrice;
        this.unit = unit;
        this.amount = amount;
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
}
