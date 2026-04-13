package org.mailosz.crmrest.prices.request;

import jakarta.validation.constraints.*;
import org.mailosz.crmrest.prices.SellingUnit;
import org.mailosz.crmrest.product.Category;

import java.math.BigDecimal;
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
    private Category category;

    @NotNull
    private SellingUnit unit;

    public ProductUpdateReq(UUID id, String name, String internalName, BigDecimal unitPrice, Category category, SellingUnit unit) {
        this.id = id;
        this.name = name;
        this.internal = internalName;
        this.unitPrice = unitPrice;
        this.category = category;
        this.unit = unit;
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

    public @NotNull Category getCategory() {
        return category;
    }

    public @NotNull SellingUnit getUnit() {
        return unit;
    }
}
