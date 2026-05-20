package org.mailosz.crmrest.crmclient.response;

import org.mailosz.crmrest.product.Product;

import java.util.List;

public class ShortPriceListResp {
    private String id;
    private List<Product> items;

    public ShortPriceListResp(String id, List<Product> items) {
        this.id = id;
        this.items = items;
    }

    public String getId() {
        return id;
    }

    public List<Product> getItems() {
        return items;
    }
}
