package org.mailosz.crmrest.prices.request;


import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

import java.util.List;
import java.util.UUID;

public class PriceListUpdateReq {
    private UUID listId;

    @Valid
    @NotNull
    private List<ProductUpdateReq> products;

    public PriceListUpdateReq(UUID listId, List<ProductUpdateReq> products) {
        this.listId = listId;
        this.products = products;
    }

    public PriceListUpdateReq() {
    }

    public UUID getListId() {
        return listId;
    }

    public void setListId(UUID listId) {
        this.listId = listId;
    }

    public List<ProductUpdateReq> getProducts() {
        return products;
    }

    public void setProducts(List<ProductUpdateReq> products) {
        this.products = products;
    }
}
