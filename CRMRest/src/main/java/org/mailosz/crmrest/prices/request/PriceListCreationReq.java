package org.mailosz.crmrest.prices.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.List;

public class PriceListCreationReq {

    @NotBlank
    @Size(max=40)
    private String listTitle;

    @NotBlank
    private String clientId;

    @Valid
    private List<ListProduct> items;

    @Valid
    private List<BaseItem> baseItems;

    public PriceListCreationReq(String listTitle, String clientId, List<ListProduct> customItems, List<BaseItem> baseItems) {
        this.listTitle = listTitle;
        this.clientId = clientId;
        this.items = customItems;
        this.baseItems = baseItems;
    }

    public @NotBlank String getListTitle() {
        return listTitle;
    }

    public @NotBlank String getClientId() {
        return clientId;
    }

    public @Valid List<ListProduct> getItems() {
        return items;
    }

    public @Valid List<BaseItem> getBaseItems() {
        return baseItems;
    }
}
