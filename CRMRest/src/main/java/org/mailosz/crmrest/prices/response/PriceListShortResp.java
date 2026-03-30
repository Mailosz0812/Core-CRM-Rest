package org.mailosz.crmrest.prices.response;

import java.time.OffsetDateTime;

public class PriceListShortResp {
    private String id;
    private String title;
    private OffsetDateTime createdAt;

    public PriceListShortResp(String id, String title, OffsetDateTime createdAt) {
        this.id = id;
        this.title = title;
        this.createdAt = createdAt;
    }

    public PriceListShortResp() {
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
}
