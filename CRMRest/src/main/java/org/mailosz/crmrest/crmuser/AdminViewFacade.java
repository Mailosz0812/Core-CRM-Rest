package org.mailosz.crmrest.crmuser;

import org.mailosz.crmrest.crmuser.response.AdminDashboardView;
import org.mailosz.crmrest.sales.SaleService;
import org.mailosz.crmrest.sales.response.ShortSaleResp;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AdminViewFacade {
    private final SaleService saleService;

    public AdminViewFacade(SaleService saleService) {
        this.saleService = saleService;
    }

    public AdminDashboardView getAdminDashboardInfo(){
        Pageable pageReq = PageRequest.of(0,15, Sort.by("createdAt").descending());
        List<ShortSaleResp> sales = saleService.getAllSales(pageReq,null,null);
        return new AdminDashboardView(sales);
    }
}
