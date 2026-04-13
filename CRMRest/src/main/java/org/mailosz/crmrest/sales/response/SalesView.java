package org.mailosz.crmrest.sales.response;

import org.mailosz.crmrest.product.Product;

import java.util.List;

public class SalesView {
    private SaleCreationResp saleResponse;
    private List<Product> priceListItems;

    public SalesView(SaleCreationResp saleResponse, List<Product> priceListItems) {
        this.saleResponse = saleResponse;
        this.priceListItems = priceListItems;
    }

    public SaleCreationResp getSaleResponse() {
        return saleResponse;
    }

    public void setSaleResponse(SaleCreationResp saleResponse) {
        this.saleResponse = saleResponse;
    }

    public List<Product> getPriceListItems() {
        return priceListItems;
    }

    public void setPriceListItems(List<Product> priceListItems) {
        this.priceListItems = priceListItems;
    }
}
