package org.mailosz.crmrest.prices.request;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.mailosz.crmrest.helpers.validator.TpsDate;
import org.mailosz.crmrest.prices.OperationType;
import org.mailosz.crmrest.prices.ProductOperation;
import org.mailosz.crmrest.prices.SellingUnit;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.UUID;

public class ProductUpdateOperation extends ProductOperation {

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

    public ProductUpdateOperation(OperationType operationType, UUID id, String name, String internal, BigDecimal unitPrice, SellingUnit unit, OffsetDateTime tps, String producer, String pack, UUID category) {
        super(operationType);
        this.id = id;
        this.name = name;
        this.internal = internal;
        this.unitPrice = unitPrice;
        this.unit = unit;
        this.tps = tps;
        this.producer = producer;
        this.pack = pack;
        this.category = category;
    }

    public UUID getId() {
        return id;
    }

    public @NotBlank @Size(max = 50) String getName() {
        return name;
    }

    public @NotBlank @Size(max = 50) String getInternal() {
        return internal;
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

    public @NotBlank @Size(max = 50) String getProducer() {
        return producer;
    }

    public @Size(max = 30) String getPack() {
        return pack;
    }

    public @NotNull UUID getCategory() {
        return category;
    }
}
