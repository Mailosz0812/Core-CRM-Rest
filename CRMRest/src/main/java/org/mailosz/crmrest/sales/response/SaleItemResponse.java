package org.mailosz.crmrest.sales.response;

import org.mailosz.crmrest.prices.SellingUnit;

import java.math.BigDecimal;

public class SaleItemResponse {
    private String saleItemId;
    private String prodId;
    private String name;
    private BigDecimal unitPrice;
    private SellingUnit unit;
    private BigDecimal amount;
    private BigDecimal sumPrice;
    private String internal;

    public SaleItemResponse(String saleItemId, String prodId, String name, BigDecimal unitPrice,
                            SellingUnit unit, BigDecimal amount, BigDecimal sumPrice, String internal) {
        this.saleItemId = saleItemId;
        this.prodId = prodId;
        this.name = name;
        this.unitPrice = unitPrice;
        this.unit = unit;
        this.amount = amount;
        this.sumPrice = sumPrice;
        this.internal = internal;
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

    public SellingUnit getUnit() {
        return unit;
    }

    public void setUnit(SellingUnit unit) {
        this.unit = unit;
    }

    public String getInternal() {
        return internal;
    }

    public void setInternal(String internal) {
        this.internal = internal;
    }

    public String getProdId() {
        return prodId;
    }

    public void setProdId(String prodId) {
        this.prodId = prodId;
    }
}
