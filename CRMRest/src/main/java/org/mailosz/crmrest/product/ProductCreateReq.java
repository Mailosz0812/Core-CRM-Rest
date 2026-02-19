package org.mailosz.crmrest.product;

import java.math.BigDecimal;

public class ProductCreateReq {
    private BigDecimal unitPrice;
    private String category;
    private String cacheId;

    public ProductCreateReq(BigDecimal unitPrice, String category, String cacheId) {
        this.unitPrice = unitPrice;
        this.category = category;
        this.cacheId = cacheId;
    }

    public void setUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setCacheId(String cacheId) {
        this.cacheId = cacheId;
    }

    public ProductCreateReq() {
    }

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public String getCategory(){
        return this.category;
    }

    public String getCacheId() {
        return cacheId;
    }
}
