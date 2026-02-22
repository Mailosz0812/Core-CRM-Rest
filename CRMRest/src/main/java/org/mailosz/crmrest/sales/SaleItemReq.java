package org.mailosz.crmrest.sales;

import java.math.BigDecimal;

public class SaleItemReq {
    private String prodCacheId;
    private String productId;
    private String name;
    private BigDecimal unitPrice;
    private BigDecimal amount;

    public SaleItemReq() {
    }

    public SaleItemReq(String prodCacheId, String productId, String name, BigDecimal unitPrice, BigDecimal amount) {
        this.prodCacheId = prodCacheId;
        this.productId = productId;
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

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
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
