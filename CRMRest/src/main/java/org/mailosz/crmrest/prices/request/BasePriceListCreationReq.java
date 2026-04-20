package org.mailosz.crmrest.prices.request;


import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public class BasePriceListCreationReq {

    @NotEmpty
    @Valid
    private List<ProductUpdateReq> productList;

    public BasePriceListCreationReq(List<@Valid ProductUpdateReq> productList) {
        this.productList = productList;
    }

    public @NotEmpty @Valid List<@Valid ProductUpdateReq> getProductList() {
        return productList;
    }

    public void setProductList(@NotEmpty @Valid List<@Valid ProductUpdateReq> productList) {
        this.productList = productList;
    }
}
