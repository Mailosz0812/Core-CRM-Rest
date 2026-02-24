package org.mailosz.crmrest.sales;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import org.hibernate.validator.constraints.UUID;

import java.util.List;


public class SaleCreateReq {

    @NotBlank
    @UUID
    private String clientId;

    @NotBlank
    private String userId;

    @NotEmpty
    @Valid
    private List<SaleItemReq> saleItems;

    @NotBlank
    private String saleData;

    public SaleCreateReq(String clientId, String userId,
                         List<SaleItemReq> saleItems, String saleData) {
        this.clientId = clientId;
        this.userId = userId;
        this.saleItems = saleItems;
        this.saleData = saleData;
    }

    public SaleCreateReq() {
    }

    public String getSaleData() {
        return saleData;
    }

    public void setSaleData(String saleData) {
        this.saleData = saleData;
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
