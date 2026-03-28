package org.mailosz.crmrest.prices.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;

import java.util.List;

public class PriceListCreationReq {

    @NotBlank
    private String listTitle;

    @NotBlank
    private String clientId;

    @NotEmpty
    @Valid
    private List<ListProduct> items;

    public PriceListCreationReq(String listTitle, String clientId, List<ListProduct> items) {
        this.listTitle = listTitle;
        this.clientId = clientId;
        this.items = items;
    }

    public @NotBlank String getListTitle() {
        return listTitle;
    }

    public @NotBlank String getClientId() {
        return clientId;
    }

    public @NotEmpty @Valid List<ListProduct> getItems() {
        return items;
    }

}
