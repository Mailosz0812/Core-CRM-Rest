package org.mailosz.crmrest.prices;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.mailosz.crmrest.prices.request.ProductUpdateReq;

public class ProductOperation {
    private Boolean delete;

    @NotNull
    @Valid
    private ProductUpdateReq prodReq;

    public ProductOperation(Boolean delete, ProductUpdateReq prodReq) {
        this.delete = delete;
        this.prodReq = prodReq;
    }

    public Boolean getDelete() {
        return delete;
    }

    public @NotNull @Valid ProductUpdateReq getProdReq() {
        return prodReq;
    }

}
