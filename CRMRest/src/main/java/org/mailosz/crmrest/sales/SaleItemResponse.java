package org.mailosz.crmrest.sales;

import java.math.BigDecimal;

public class SaleItemResponse {
    private String saleItemId;
    private String name;
    private BigDecimal unitPrice;
    private BigDecimal amount;
    private BigDecimal sumPrice;

    public SaleItemResponse(String saleItemId, String name, BigDecimal unitPrice, BigDecimal amount, BigDecimal sumPrice) {
        this.saleItemId = saleItemId;
        this.name = name;
        this.unitPrice = unitPrice;
        this.amount = amount;
        this.sumPrice = sumPrice;
    }

    public SaleItemResponse(){}

    public String getSaleItemId() {
        return saleItemId;
    }

    public BigDecimal getSumPrice() {
        return sumPrice;
    }

    public void setSumPrice(BigDecimal sumPrice) {
        this.sumPrice = sumPrice;
    }

    public void setSaleItemId(String saleItemId) {
        this.saleItemId = saleItemId;
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

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
}
