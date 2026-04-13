package org.mailosz.crmrest.prices.request;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.mailosz.crmrest.prices.SellingUnit;
import org.mailosz.crmrest.product.Category;

import java.math.BigDecimal;

public class ListProduct {

    @NotBlank
    private String name;

    @NotBlank
    private String internalName;

    @NotNull
    @DecimalMin(value = "0.00",message = "Price should be greater than zero")
    private BigDecimal unitPrice;

    @NotNull
    private SellingUnit unit;

    @NotNull
    private Category prodCategory;

    public ListProduct(String name, String internalName, BigDecimal unitPrice, SellingUnit unit, Category prodCategory) {
        this.name = name;
        this.internalName = internalName;
        this.unitPrice = unitPrice;
        this.unit = unit;
        this.prodCategory = prodCategory;
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
}
