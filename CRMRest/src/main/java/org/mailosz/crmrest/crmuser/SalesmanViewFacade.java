package org.mailosz.crmrest.crmuser;

import org.mailosz.crmrest.crmuser.response.SalesmanDashboardView;
import org.mailosz.crmrest.sales.SaleService;
import org.mailosz.crmrest.sales.response.ShortSaleResp;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SalesmanViewFacade {
    private final SaleService saleService;

    public SalesmanViewFacade(SaleService saleService) {
        this.saleService = saleService;
    }

    public SalesmanDashboardView getSalesmanDashboardInfo(String username){
        Pageable pageReq = PageRequest.of(0,15, Sort.by("createdAt").descending());
        List<ShortSaleResp> userSales = this.saleService.getSalesByUser(username,pageReq);
        return new SalesmanDashboardView(userSales);
    }
}
