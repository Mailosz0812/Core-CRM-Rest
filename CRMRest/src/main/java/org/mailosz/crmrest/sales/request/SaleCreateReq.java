package org.mailosz.crmrest.sales.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.List;
import java.util.UUID;


public class SaleCreateReq {

    @NotNull
    private UUID clientId;

    @Valid
    private List<SaleItemReq> saleItems;

    @Valid
    private List<CustomSaleItem> customItems;

    @Size(max=100)
    private String saleData;

    @Size(max=100)
    private String warehouseNote;

    @NotBlank
    @Size(max=40)
    private String saleName;


    public SaleCreateReq() {
    }

    public SaleCreateReq(UUID clientId, List<SaleItemReq> saleItems, List<CustomSaleItem> customItems, String saleData, String warehouseNote, String saleName) {
        this.clientId = clientId;
        this.saleItems = saleItems;
        this.customItems = customItems;
        this.saleData = saleData;
        this.warehouseNote = warehouseNote;
        this.saleName = saleName;
    }

    public @NotNull UUID getClientId() {
        return clientId;
    }

    public void setClientId(@NotNull UUID clientId) {
        this.clientId = clientId;
    }

    public @NotNull @Valid List<SaleItemReq> getSaleItems() {
        return saleItems;
    }

    public void setSaleItems(@NotNull @Valid List<SaleItemReq> saleItems) {
        this.saleItems = saleItems;
    }

    public List<CustomSaleItem> getCustomItems() {
        return customItems;
    }

    public void setCustomItems(List<CustomSaleItem> customItems) {
        this.customItems = customItems;
    }

    public String getSaleData() {
        return saleData;
    }

    public void setSaleData(String saleData) {
        this.saleData = saleData;
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
}
