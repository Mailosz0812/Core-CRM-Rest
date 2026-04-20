package org.mailosz.crmrest.product;

import org.mailosz.crmrest.prices.SellingUnit;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

public class Product {
    private String id;
    private String name;
    private BigDecimal unitPrice;
    private SellingUnit unit;
    private String category;
    private String internal;
    private OffsetDateTime tps;


    public Product(String id, String name, BigDecimal unitPrice, SellingUnit unit, String category, String internal, OffsetDateTime tps) {
        this.id = id;
        this.name = name;
        this.unitPrice = unitPrice;
        this.unit = unit;
        this.category = category;
        this.internal = internal;
        this.tps = tps;
    }

    public Product() {
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

    public String getInternal() {
        return internal;
    }

    public void setInternal(String internal) {
        this.internal = internal;
    }

    public OffsetDateTime getTps() {
        return tps;
    }

    public void setTps(OffsetDateTime tps) {
        this.tps = tps;
    }
}
