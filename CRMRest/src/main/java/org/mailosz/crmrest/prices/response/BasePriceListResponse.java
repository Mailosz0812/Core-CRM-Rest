package org.mailosz.crmrest.prices.response;

import org.mailosz.crmrest.product.Product;

import java.util.List;

public class BasePriceListResponse {
    private List<Product> productList;

    public BasePriceListResponse(List<Product> productList) {
        this.productList = productList;
    }

    public List<Product> getProductList() {
        return productList;
    }
}
