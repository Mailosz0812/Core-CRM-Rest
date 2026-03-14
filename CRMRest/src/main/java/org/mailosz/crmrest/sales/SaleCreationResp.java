package org.mailosz.crmrest.sales;

import java.math.BigDecimal;
import java.util.List;

public class SaleCreationResp {
    private String saleId;
    private String saleData;
    private List<SaleItemResponse> saleItems;
    private String stage;
    private BigDecimal sumPrice;

    public SaleCreationResp(String saleId, String saleData, List<SaleItemResponse> saleItems, String stage, BigDecimal sumPrice) {
        this.saleId = saleId;
        this.saleData = saleData;
        this.saleItems = saleItems;
        this.stage = stage;
        this.sumPrice = sumPrice;
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

    public String getStage() {
        return stage;
    }

    public void setStage(String stage) {
        this.stage = stage;
    }
}
