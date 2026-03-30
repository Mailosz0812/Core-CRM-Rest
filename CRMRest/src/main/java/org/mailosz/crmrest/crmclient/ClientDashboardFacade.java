package org.mailosz.crmrest.crmclient;

import org.mailosz.crmrest.crmclient.response.ClientDashboardView;
import org.mailosz.crmrest.crmclient.response.ClientWidgetResponse;
import org.mailosz.crmrest.prices.PriceListService;
import org.mailosz.crmrest.product.Product;
import org.mailosz.crmrest.sales.SaleService;
import org.mailosz.crmrest.sales.ShortSaleResp;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;


@Service
public class ClientDashboardFacade {
    private final ClientService clientService;
    private final SaleService saleService;
    private final PriceListService priceService;

    public ClientDashboardFacade(ClientService clientService, SaleService saleService, PriceListService priceService) {
        this.clientService = clientService;
        this.saleService = saleService;
        this.priceService = priceService;
    }
    public ClientDashboardView getClientDashboardData(UUID id){
        ClientWidgetResponse clientInfo = clientService.getClientWidgetInfo(id);
        List<ShortSaleResp> sales = this.saleService.getSalesByClientId(id, Pageable.ofSize(5));
        List<Product> latestPrices = this.priceService.getLatestProductsByClientId(id);

        return new ClientDashboardView(clientInfo,sales,latestPrices);
    }
}
