package org.mailosz.crmrest.crmuser.response;

import org.mailosz.crmrest.sales.response.ShortSaleResp;
import org.mailosz.crmrest.stats.response.StatsResponse;

import java.util.List;

public class AdminDashboardView {
    private List<ShortSaleResp> sales;
    private StatsResponse stats;
//    private List<Reminder> reminders


    public AdminDashboardView(List<ShortSaleResp> sales, StatsResponse stats) {
        this.sales = sales;
        this.stats = stats;
    }

    public List<ShortSaleResp> getSales() {
        return sales;
    }

    public StatsResponse getStats() {
        return stats;
    }
}
