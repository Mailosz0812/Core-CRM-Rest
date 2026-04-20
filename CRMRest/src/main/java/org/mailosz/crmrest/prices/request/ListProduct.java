package org.mailosz.crmrest.prices.request;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.mailosz.crmrest.helpers.validator.TpsDate;
import org.mailosz.crmrest.prices.SellingUnit;
import org.mailosz.crmrest.product.Category;

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
    private Category prodCategory;

    @NotNull
    @TpsDate
    private OffsetDateTime tps;

    public ListProduct(String name, String internalName, BigDecimal unitPrice,
                       SellingUnit unit, Category prodCategory, OffsetDateTime tps) {
        this.name = name;
        this.internalName = internalName;
        this.unitPrice = unitPrice;
        this.unit = unit;
        this.prodCategory = prodCategory;
        this.tps = tps;
    }

    public ListProduct() {
    }

    public @NotBlank String getName() {
        return name;
    }

    public @NotNull @DecimalMin(value = "0.00", message = "Price should be greater than zero") BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public @NotNull SellingUnit getUnit() {
        return unit;
    }

    public @NotNull Category getProdCategory() {
        return prodCategory;
    }

    public @NotBlank String getInternalName() {
        return internalName;
    }

    public @NotNull OffsetDateTime getTps() {
        return tps;
    }
}
