package org.mailosz.crmrest.crmclient;

import org.mailosz.crmrest.crmclient.response.ClientWidgetResponse;
import org.mailosz.crmrest.sales.ShortSaleResp;

import java.util.List;

public class ClientDashboardView {
    private ClientWidgetResponse clientInfo;
    private List<ShortSaleResp> recentSales;

    public ClientDashboardView(ClientWidgetResponse clientInfo, List<ShortSaleResp> recentSales) {
        this.clientInfo = clientInfo;
        this.recentSales = recentSales;
    }

    public ClientDashboardView() {
    }

    public ClientWidgetResponse getClientInfo() {
        return clientInfo;
    }

    public List<ShortSaleResp> getRecentSales() {
        return recentSales;
    }
}
