package org.mailosz.crmrest.sales;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public class SaleItemReq {

    @NotBlank
    private String prodCacheId;

    @NotBlank
    private String name;

    @DecimalMin(value = "0.01",message = "Price should be greater than zero")
    private BigDecimal unitPrice;

    @Positive(message = "Amount should be greater than zero")
    private BigDecimal amount;

    public SaleItemReq() {
    }

    public SaleItemReq(String prodCacheId, String name, BigDecimal unitPrice, BigDecimal amount) {
        this.prodCacheId = prodCacheId;
        this.name = name;
        this.unitPrice = unitPrice;
        this.amount = amount;
    }

    public String getProdCacheId() {
        return prodCacheId;
    }

    public void setProdCacheId(String prodCacheId) {
        this.prodCacheId = prodCacheId;
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
