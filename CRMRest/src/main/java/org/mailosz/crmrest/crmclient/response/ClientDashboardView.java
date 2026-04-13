package org.mailosz.crmrest.crmclient.response;

import org.mailosz.crmrest.product.Product;
import org.mailosz.crmrest.sales.response.ShortSaleResp;

import java.util.List;

public class ClientDashboardView {
    private ClientWidgetResponse clientInfo;
    private List<ShortSaleResp> recentSales;
    private List<Product> recentPrices;


    public ClientDashboardView(ClientWidgetResponse clientInfo, List<ShortSaleResp> recentSales, List<Product> recentPrices) {
        this.clientInfo = clientInfo;
        this.recentSales = recentSales;
        this.recentPrices = recentPrices;
    }

    public ClientDashboardView() {}

    public ClientWidgetResponse getClientInfo() {
        return clientInfo;
    }

    public List<ShortSaleResp> getRecentSales() {
        return recentSales;
    }

    public List<Product> getRecentPrices() {
        return recentPrices;
    }
}
