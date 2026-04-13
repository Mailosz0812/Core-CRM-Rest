package org.mailosz.crmrest.crmuser.response;

import org.mailosz.crmrest.sales.response.ShortSaleResp;

import java.util.List;

public class SalesmanDashboardView {
    private List<ShortSaleResp> sales;
//    private SalesmanStats salesmanStats;
//    private List<Reminders> reminders;


    public SalesmanDashboardView(List<ShortSaleResp> sales) {
        this.sales = sales;
    }

    public List<ShortSaleResp> getSales() {
        return sales;
    }
}
