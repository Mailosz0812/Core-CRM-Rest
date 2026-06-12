package org.mailosz.crmrest.prices.request;

import jakarta.validation.constraints.*;
import org.mailosz.crmrest.helpers.validator.TpsDate;
import org.mailosz.crmrest.prices.SellingUnit;
import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.UUID;

public class ProductUpdateReq {

    private UUID id;

    @NotBlank
    @Size(max=50)
    private String name;

    @NotBlank
    @Size(max=50)
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
    @Size(max=50)
    private String producer;

    @Size(max=30)
    private String pack;

    @NotNull
    private UUID category;

    public ProductUpdateReq(UUID id, String name, String internal, BigDecimal unitPrice, SellingUnit unit, OffsetDateTime tps, String producer, String pack, UUID categoryId) {
        this.id = id;
        this.name = name;
        this.internal = internal;
        this.unitPrice = unitPrice;
        this.unit = unit;
        this.tps = tps;
        this.producer = producer;
        this.pack = pack;
        this.category = categoryId;
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

    public @NotNull UUID getCategory() {
        return category;
    }
}
