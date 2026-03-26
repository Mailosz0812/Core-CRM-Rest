package org.mailosz.crmrest.crmclient;

import org.mailosz.crmrest.crmclient.response.ClientDashboardView;
import org.mailosz.crmrest.crmclient.response.ClientWidgetResponse;
import org.mailosz.crmrest.sales.SaleService;
import org.mailosz.crmrest.sales.ShortSaleResp;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ClientDashboardFacade {
    private final ClientService clientService;
    private final SaleService saleService;

    public ClientDashboardFacade(ClientService clientService, SaleService saleService) {
        this.clientService = clientService;
        this.saleService = saleService;
    }

    public ClientDashboardView getClientDashboardData(String id){
        ClientWidgetResponse clientInfo = clientService.getClientWidgetInfo(id);
        List<ShortSaleResp> sales = this.saleService.getSalesByClientId(id, Pageable.ofSize(5));
        return new ClientDashboardView(clientInfo,sales);
    }
}
