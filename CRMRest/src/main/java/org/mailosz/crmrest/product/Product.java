package org.mailosz.crmrest.product;

import org.mailosz.crmrest.prices.SellingUnit;

import java.math.BigDecimal;

public class ProductResponse {
    private String id;
    private String name;
    private BigDecimal unitPrice;
    private SellingUnit unit;
    private String category;

    public ProductResponse(String id, String name, BigDecimal unitPrice, SellingUnit unit, String category) {
        this.id = id;
        this.name = name;
        this.unitPrice = unitPrice;
        this.unit = unit;
        this.category = category;
    }

    public ProductResponse() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
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

    public SellingUnit getUnit() {
        return unit;
    }

    public void setUnit(SellingUnit unit) {
        this.unit = unit;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
