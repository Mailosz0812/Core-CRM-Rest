package org.mailosz.crmrest.prices.request;


import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

import java.util.List;
import java.util.UUID;

public class PriceListUpdateReq {

    @NotNull
    private UUID listId;

    @Valid
    private List<ProductUpdateReq> products;

    @Valid
    private List<BaseItem> baseItems;

    public PriceListUpdateReq(UUID listId, List<ProductUpdateReq> products, List<BaseItem> baseItems) {
        this.listId = listId;
        this.products = products;
        this.baseItems = baseItems;
    }

    public PriceListUpdateReq() {}

    public UUID getListId() {
        return listId;
    }

    public List<ProductUpdateReq> getProducts() {
        return products;
    }

    public List<BaseItem> getBaseItems() {
        return baseItems;
    }
}
