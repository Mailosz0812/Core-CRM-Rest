package org.mailosz.crmrest.product;

import java.math.BigDecimal;

public class ProductResponse {
    private String id;
    private String name;
    private BigDecimal unitPrice;
    private String category;

    public ProductResponse(String id, String name, BigDecimal unitPrice, String category) {
        this.id = id;
        this.name = name;
        this.unitPrice = unitPrice;
        this.category = category;
    }

    public ProductResponse() {
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public String getCategory() {
        return category;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
