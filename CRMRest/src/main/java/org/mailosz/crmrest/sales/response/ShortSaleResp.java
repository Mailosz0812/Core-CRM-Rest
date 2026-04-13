package org.mailosz.crmrest.sales.response;

import org.mailosz.crmrest.sales.Stage;

public class ShortSaleResp {
    private String saleId;
    private String saleName;
    private Stage stage;
    private String sumPrice;
    private String clientName;

    public ShortSaleResp(String saleId, String saleName,
                         Stage stage, String sumPrice, String clientName) {
        this.saleId = saleId;
        this.saleName = saleName;
        this.stage = stage;
        this.sumPrice = sumPrice;
        this.clientName = clientName;
    }

    public String getSaleId() {
        return saleId;
    }

    public void setSaleId(String saleId) {
        this.saleId = saleId;
    }

    public Stage getStage() {
        return stage;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public String getSumPrice() {
        return sumPrice;
    }

    public void setSumPrice(String sumPrice) {
        this.sumPrice = sumPrice;
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
}
