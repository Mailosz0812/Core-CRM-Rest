package org.mailosz.crmrest.sales;

public class ShortSaleResp {
    private String saleId;
    private String saleData;
    private String clientName;
    private String sumPrice;

    public ShortSaleResp(String saleId, String saleData,
                         String clientName, String sumPrice) {
        this.saleId = saleId;
        this.saleData = saleData;
        this.clientName = clientName;
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

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public String getSumPrice() {
        return sumPrice;
    }

    public void setSumPrice(String sumPrice) {
        this.sumPrice = sumPrice;
    }
}
