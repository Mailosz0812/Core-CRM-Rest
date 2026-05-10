package org.mailosz.crmrest.prices.request;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.mailosz.crmrest.helpers.validator.TpsDate;
import org.mailosz.crmrest.prices.SellingUnit;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

public class ListProduct {

    @NotBlank
    private String name;

    @NotBlank
    private String internalName;

    @NotNull
    @DecimalMin(value = "0.00",message = "Price should be greater than or equal zero")
    private BigDecimal unitPrice;

    @NotNull
    private SellingUnit unit;

    @NotNull
    @TpsDate
    private OffsetDateTime tps;

    @NotBlank
    private String producer;

    private String pack;

    public ListProduct(String name, String internalName, BigDecimal unitPrice,
                       SellingUnit unit, OffsetDateTime tps, String producer, String pack) {
        this.name = name;
        this.internalName = internalName;
        this.unitPrice = unitPrice;
        this.unit = unit;
        this.tps = tps;
        this.producer = producer;
        this.pack = pack;
    }

    public ListProduct() {
    }

    public @NotBlank String getName() {
        return name;
    }

    public @NotBlank String getInternalName() {
        return internalName;
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

    public @NotBlank String getProducer() {
        return producer;
    }

    public String getPack() {
        return pack;
    }
}
