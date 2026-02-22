package org.mailosz.crmrest.sales;

import java.util.List;


public class SaleCreateReq {
    private String clientId;
    private String userId;
    private List<SaleItemReq> saleItems;

    public SaleCreateReq(String clientId, String userId, List<SaleItemReq> saleItems) {
        this.clientId = clientId;
        this.userId = userId;
        this.saleItems = saleItems;
    }

    public SaleCreateReq() {
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public List<SaleItemReq> getSaleItems() {
        return saleItems;
    }

    public void setSaleItems(List<SaleItemReq> saleItems) {
        this.saleItems = saleItems;
    }
}
