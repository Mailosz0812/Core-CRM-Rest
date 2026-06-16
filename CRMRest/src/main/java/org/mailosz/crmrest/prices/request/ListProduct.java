package org.mailosz.crmrest.prices.request;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.mailosz.crmrest.helpers.validator.TpsDate;
import org.mailosz.crmrest.prices.SellingUnit;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.UUID;

public class ListProduct {

    @NotBlank
    @Size(max=50)
    private String name;

    @NotBlank
    @Size(max=50)
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
    @Size(max=50)
    private String producer;

    @Size(max=30)
    private String pack;

    @NotNull
    private UUID categoryId;

    public ListProduct(String name, String internalName, BigDecimal unitPrice, SellingUnit unit, OffsetDateTime tps, String producer, String pack, UUID categoryId) {
        this.name = name;
        this.internalName = internalName;
        this.unitPrice = unitPrice;
        this.unit = unit;
        this.tps = tps;
        this.producer = producer;
        this.pack = pack;
        this.categoryId = categoryId;
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

    public @NotNull UUID getCategoryId() {
        return categoryId;
    }
}
