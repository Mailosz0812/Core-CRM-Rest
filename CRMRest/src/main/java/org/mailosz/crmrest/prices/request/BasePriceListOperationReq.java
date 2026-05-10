package org.mailosz.crmrest.prices.request;


import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import org.mailosz.crmrest.prices.ProductOperation;

import java.util.List;

public class BasePriceListOperationReq {

    @NotEmpty
    @Valid
    private List<ProductOperation> productList;

    public BasePriceListOperationReq(List<ProductOperation> productList) {
        this.productList = productList;
    }

    public @NotEmpty @Valid List<ProductOperation> getProductList() {
        return productList;
    }
}
