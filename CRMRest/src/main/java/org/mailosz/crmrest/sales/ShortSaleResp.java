package org.mailosz.crmrest.sales;

public class ShortSaleResp {
    private String saleId;
    private String saleData;
    private String stage;
    private String sumPrice;

    public ShortSaleResp(String saleId, String saleData, String stage, String sumPrice) {
        this.saleId = saleId;
        this.saleData = saleData;
        this.stage = stage;
        this.sumPrice = sumPrice;
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

    public String getStage() {
        return stage;
    }

    public void setStage(String stage) {
        this.stage = stage;
    }

    public String getSumPrice() {
        return sumPrice;
    }

    public void setSumPrice(String sumPrice) {
        this.sumPrice = sumPrice;
    }
}
