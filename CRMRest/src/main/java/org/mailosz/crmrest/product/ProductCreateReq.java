package org.mailosz.crmrest.product;

import java.math.BigDecimal;

public class ProductCreateReq {
    private BigDecimal unitPrice;
    private String productName;
    private String category;
    private String cacheId;
    private String clientId;

    public ProductCreateReq(BigDecimal unitPrice, String productName, String category, String cacheId, String clientId) {
        this.unitPrice = unitPrice;
        this.productName = productName;
        this.category = category;
        this.cacheId = cacheId;
        this.clientId = clientId;
    }

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getCacheId() {
        return cacheId;
    }

    public void setCacheId(String cacheId) {
        this.cacheId = cacheId;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }
}
