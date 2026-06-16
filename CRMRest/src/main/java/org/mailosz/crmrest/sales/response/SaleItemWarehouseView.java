package org.mailosz.crmrest.sales.response;

import org.mailosz.crmrest.prices.SellingUnit;

import java.math.BigDecimal;

public class SaleItemWarehouseView {
    private String saleItemId;
    private String name;
    private SellingUnit unit;
    private BigDecimal amount;
    private String internal;
    private String category;
    private String producer;

    public SaleItemWarehouseView(String saleItemId, String name, SellingUnit unit,
                                 BigDecimal amount, String internal,
                                 String category, String producer) {
        this.saleItemId = saleItemId;
        this.name = name;
        this.unit = unit;
        this.amount = amount;
        this.internal = internal;
        this.category = category;
        this.producer = producer;
    }

    public String getSaleItemId() {
        return saleItemId;
    }

    public String getName() {
        return name;
    }

    public SellingUnit getUnit() {
        return unit;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public String getInternal() {
        return internal;
    }

    public String getCategory() {
        return category;
    }

    public String getProducer() {
        return producer;
    }
}
