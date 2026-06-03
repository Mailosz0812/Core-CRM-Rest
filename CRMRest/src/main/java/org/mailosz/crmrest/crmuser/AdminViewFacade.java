package org.mailosz.crmrest.crmuser;

import org.mailosz.crmrest.crmuser.response.AdminDashboardView;
import org.mailosz.crmrest.sales.SaleService;
import org.mailosz.crmrest.sales.response.ShortSaleResp;
import org.mailosz.crmrest.stats.StatsService;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AdminViewFacade {
    private final SaleService saleService;
    private final StatsService statsService;

    public AdminViewFacade(SaleService saleService, StatsService statsService) {
        this.saleService = saleService;
        this.statsService = statsService;
    }

    public AdminDashboardView getAdminDashboardInfo(){
        Pageable pageReq = PageRequest.of(0,15, Sort.by("createdAt").descending());
        List<ShortSaleResp> sales = saleService.getAllSales(pageReq,null,null);
        return new AdminDashboardView(sales,statsService.findMonthlyStats());
    }
}
