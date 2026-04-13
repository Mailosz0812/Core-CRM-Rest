package org.mailosz.crmrest.sales.response;

import org.mailosz.crmrest.sales.Stage;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.List;

public class SaleCreationResp {
    private String saleId;
    private String saleData;
    private String warehouseNote;
    private List<SaleItemResponse> saleItems;
    private Stage stage;
    private BigDecimal sumPrice;
    private String saleName;
    private String clientId;
    private String clientName;
    private OffsetDateTime createdAt;

    public SaleCreationResp(String saleId, String saleData,
                            String warehouseNote, List<SaleItemResponse> saleItems,
                            Stage stage, BigDecimal sumPrice,
                            String saleName, String clientId,
                            String clientName, OffsetDateTime createdAt) {
        this.saleId = saleId;
        this.saleData = saleData;
        this.warehouseNote = warehouseNote;
        this.saleItems = saleItems;
        this.stage = stage;
        this.sumPrice = sumPrice;
        this.saleName = saleName;
        this.clientId = clientId;
        this.clientName = clientName;
        this.createdAt = createdAt;
    }

    public SaleCreationResp(){}

    public String getSaleId() {
        return saleId;
    }

    public void setSaleId(String saleId) {
        this.saleId = saleId;
    }

    public BigDecimal getSumPrice() {
        return sumPrice;
    }

    public void setSumPrice(BigDecimal sumPrice) {
        this.sumPrice = sumPrice;
    }

    public String getSaleData() {
        return saleData;
    }

    public void setSaleData(String saleData) {
        this.saleData = saleData;
    }

    public List<SaleItemResponse> getSaleItems() {
        return saleItems;
    }

    public void setSaleItems(List<SaleItemResponse> saleItems) {
        this.saleItems = saleItems;
    }

    public Stage getStage() {
        return stage;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public String getWarehouseNote() {
        return warehouseNote;
    }

    public void setWarehouseNote(String warehouseNote) {
        this.warehouseNote = warehouseNote;
    }

    public String getSaleName() {
        return saleName;
    }

    public void setSaleName(String saleName) {
        this.saleName = saleName;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public OffsetDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(OffsetDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }
}
