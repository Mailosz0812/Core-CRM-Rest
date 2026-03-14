package org.mailosz.crmrest.sales;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.List;

public class SaleResponse {
    private String saleId;
    private String saleData;
    private String stage;
    private List<SaleItemResponse> itemsList;
    private OffsetDateTime createdAt;
    private OffsetDateTime updatedAt;
    private OffsetDateTime checkedAt;
    private BigDecimal sumPrice;
    private String clientName;
    private String clientNip;

    public SaleResponse(String saleId, String saleData, String stage,
                        List<SaleItemResponse> itemsList, OffsetDateTime createdAt,
                        OffsetDateTime updatedAt, OffsetDateTime checkedAt,
                        BigDecimal sumPrice, String clientName, String clientNip) {
        this.saleId = saleId;
        this.saleData = saleData;
        this.stage = stage;
        this.itemsList = itemsList;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.checkedAt = checkedAt;
        this.sumPrice = sumPrice;
        this.clientName = clientName;
        this.clientNip = clientNip;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public String getClientNip() {
        return clientNip;
    }

    public void setClientNip(String clientNip) {
        this.clientNip = clientNip;
    }

    public String getStage() {
        return stage;
    }

    public void setStage(String stage) {
        this.stage = stage;
    }

    public String getSaleId() {
        return saleId;
    }

    public void setSaleId(String saleId) {
        this.saleId = saleId;
    }

    public String getSaleData() {
        return saleData;
    }

    public void setSaleData(String saleData) {
        this.saleData = saleData;
    }

    public List<SaleItemResponse> getItemsList() {
        return itemsList;
    }

    public void setItemsList(List<SaleItemResponse> itemsList) {
        this.itemsList = itemsList;
    }

    public OffsetDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(OffsetDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public OffsetDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(OffsetDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public OffsetDateTime getCheckedAt() {
        return checkedAt;
    }

    public void setCheckedAt(OffsetDateTime checkedAt) {
        this.checkedAt = checkedAt;
    }

    public BigDecimal getSumPrice() {
        return sumPrice;
    }

    public void setSumPrice(BigDecimal sumPrice) {
        this.sumPrice = sumPrice;
    }
}
