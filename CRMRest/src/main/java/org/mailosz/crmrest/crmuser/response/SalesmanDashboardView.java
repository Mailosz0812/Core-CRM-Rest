package org.mailosz.crmrest.crmuser.response;

import org.mailosz.crmrest.sales.response.ShortSaleResp;
import org.mailosz.crmrest.stats.response.SalesmanStats;

import java.util.List;

public class SalesmanDashboardView {
    private List<ShortSaleResp> sales;
    private SalesmanStats salesmanStats;
//    private List<Reminders> reminders;


    public SalesmanDashboardView(List<ShortSaleResp> sales, SalesmanStats salesmanStats) {
        this.sales = sales;
        this.salesmanStats = salesmanStats;
    }

    public List<ShortSaleResp> getSales() {
        return sales;
    }

    public SalesmanStats getSalesmanStats() {
        return salesmanStats;
    }
}
