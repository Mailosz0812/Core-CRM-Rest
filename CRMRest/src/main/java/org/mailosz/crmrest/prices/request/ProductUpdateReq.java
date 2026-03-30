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

    @NotNull
    @DecimalMin(value = "0.00",message = "Price should be greater than zero")
    private BigDecimal unitPrice;

    @NotNull
    private Category category;

    @NotNull
    private SellingUnit unit;

    public ProductUpdateReq(UUID id, String name, BigDecimal unitPrice, Category category, SellingUnit unit) {
        this.id = id;
        this.name = name;
        this.unitPrice = unitPrice;
        this.category = category;
        this.unit = unit;
    }

    public ProductUpdateReq() {
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public SellingUnit getUnit() {
        return unit;
    }

    public void setUnit(SellingUnit unit) {
        this.unit = unit;
    }
}
