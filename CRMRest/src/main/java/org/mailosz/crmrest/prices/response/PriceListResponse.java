package org.mailosz.crmrest.prices.response;

import org.mailosz.crmrest.product.ProductResponse;

import java.time.OffsetDateTime;
import java.util.List;

public class PriceListResponse {
    private String id;
    private String title;
    private OffsetDateTime createdAt;
    private List<ProductResponse> productsList;

    public PriceListResponse(String id, String title, OffsetDateTime createdAt, List<ProductResponse> productsList) {
        this.id = id;
        this.title = title;
        this.createdAt = createdAt;
        this.productsList = productsList;
    }

    public PriceListResponse() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public OffsetDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(OffsetDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public List<ProductResponse> getProductsList() {
        return productsList;
    }

    public void setProductsList(List<ProductResponse> productsList) {
        this.productsList = productsList;
    }
}
