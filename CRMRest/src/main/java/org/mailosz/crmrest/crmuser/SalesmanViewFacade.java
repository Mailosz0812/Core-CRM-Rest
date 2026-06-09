package org.mailosz.crmrest.crmuser;

import org.mailosz.crmrest.crmuser.response.SalesmanDashboardView;
import org.mailosz.crmrest.sales.SaleService;
import org.mailosz.crmrest.sales.response.ShortSaleResp;
import org.mailosz.crmrest.stats.StatsService;
import org.mailosz.crmrest.stats.response.SalesmanStats;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SalesmanViewFacade {
    private final SaleService saleService;
    private final StatsService statsService;

    public SalesmanViewFacade(StatsService statsService, SaleService saleService) {
        this.statsService = statsService;
        this.saleService = saleService;
    }

    public SalesmanDashboardView getSalesmanDashboardInfo(String username){
        Pageable pageReq = PageRequest.of(0,15, Sort.by("createdAt").descending());
        List<ShortSaleResp> userSales = this.saleService.getSalesByUser(username,pageReq);
        SalesmanStats stats = this.statsService.findSalesmanStats(username);
        return new SalesmanDashboardView(userSales,stats);
    }
}
