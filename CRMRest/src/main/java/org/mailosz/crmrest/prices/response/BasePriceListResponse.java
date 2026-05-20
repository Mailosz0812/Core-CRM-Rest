package org.mailosz.crmrest.prices.response;

import org.mailosz.crmrest.product.Product;

import java.util.List;

public class BasePriceListResponse {
    private List<Product> productList;
    private String id;

    public BasePriceListResponse(List<Product> productList, String id) {
        this.productList = productList;
        this.id = id;
    }

    public List<Product> getProductList() {
        return productList;
    }

    public String getId() {
        return id;
    }
}
