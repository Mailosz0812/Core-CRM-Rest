package org.mailosz.crmrest.sales.response;

import java.time.OffsetDateTime;
import java.util.List;

public class SaleResponse {
    private String saleId;
    private String saleName;
    private String warehouseNote;
    private List<SaleItemWarehouseView> itemsList;
    private OffsetDateTime createdAt;
    private OffsetDateTime checkedAt;
    private OffsetDateTime packageDate;
    private String clientName;
    private String clientNip;

    public SaleResponse(String saleId, String saleName, String warehouseNote, List<SaleItemWarehouseView> itemsList,
                        OffsetDateTime createdAt, OffsetDateTime checkedAt, OffsetDateTime packageDate, String clientName, String clientNip) {
        this.saleId = saleId;
        this.saleName = saleName;
        this.warehouseNote = warehouseNote;
        this.itemsList = itemsList;
        this.createdAt = createdAt;
        this.checkedAt = checkedAt;
        this.packageDate = packageDate;
        this.clientName = clientName;
        this.clientNip = clientNip;
    }

    public String getSaleId() {
        return saleId;
    }

    public String getSaleName() {
        return saleName;
    }

    public String getWarehouseNote() {
        return warehouseNote;
    }

    public List<SaleItemWarehouseView> getItemsList() {
        return itemsList;
    }

    public OffsetDateTime getCreatedAt() {
        return createdAt;
    }

    public OffsetDateTime getCheckedAt() {
        return checkedAt;
    }

    public OffsetDateTime getPackageDate() {
        return packageDate;
    }

    public String getClientName() {
        return clientName;
    }

    public String getClientNip() {
        return clientNip;
    }
}
