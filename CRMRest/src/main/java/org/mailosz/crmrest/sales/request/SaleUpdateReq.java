package org.mailosz.crmrest.sales.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.List;
import java.util.UUID;

public class SaleUpdateReq {

    @NotNull
    private UUID saleId;

    @NotBlank
    @Size(max=40)
    private String saleName;

    @NotNull
    private List<SaleItemReq> saleItems;

    @NotNull
    private List<CustomSaleItem> customItems;

    public SaleUpdateReq(UUID saleId, String saleName,
                         List<SaleItemReq> saleItems, List<CustomSaleItem> customItems) {
        this.saleId = saleId;
        this.saleName = saleName;
        this.saleItems = saleItems;
        this.customItems = customItems;
    }

    public SaleUpdateReq() {
    }

    public UUID getSaleId() {
        return saleId;
    }

    public String getSaleName() {
        return saleName;
    }

    public List<SaleItemReq> getSaleItems() {
        return saleItems;
    }

    public List<CustomSaleItem> getCustomItems() {
        return customItems;
    }
}
