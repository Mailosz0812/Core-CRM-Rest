package org.mailosz.crmrest.prices.request;

import jakarta.validation.constraints.*;
import org.mailosz.crmrest.helpers.validator.TpsDate;
import org.mailosz.crmrest.prices.SellingUnit;
import org.mailosz.crmrest.product.Category;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.UUID;

public class ProductUpdateReq {

    private UUID id;

    @NotBlank
    private String name;

    @NotBlank
    private String internal;

    @NotNull
    @DecimalMin(value = "0.00",message = "Price should be greater than zero")
    private BigDecimal unitPrice;

    @NotNull
    private SellingUnit unit;

    @NotNull
    @TpsDate
    private OffsetDateTime tps;

    @NotBlank
    private String producer;

    private String pack;

    public ProductUpdateReq(UUID id, String name, String internal, BigDecimal unitPrice, SellingUnit unit, OffsetDateTime tps, String producer, String pack) {
        this.id = id;
        this.name = name;
        this.internal = internal;
        this.unitPrice = unitPrice;
        this.unit = unit;
        this.tps = tps;
        this.producer = producer;
        this.pack = pack;
    }

    public ProductUpdateReq() {
    }

    public UUID getId() {
        return id;
    }

    public @NotBlank String getName() {
        return name;
    }

    public @NotBlank String getInternal() {
        return internal;
    }

    public @NotNull @DecimalMin(value = "0.00", message = "Price should be greater than zero") BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public @NotNull String getProducer() {
        return producer;
    }

    public String getPack() {
        return pack;
    }

    public @NotNull SellingUnit getUnit() {
        return unit;
    }

    public @NotNull OffsetDateTime getTps() {
        return tps;
    }
}
