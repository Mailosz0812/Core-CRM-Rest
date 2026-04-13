package org.mailosz.crmrest.crmuser.response;

import org.mailosz.crmrest.sales.response.ShortSaleResp;

import java.util.List;

public class AdminDashboardView {
    private List<ShortSaleResp> sales;
//    private Statistics stats;
//    private List<Reminder> reminders

    public AdminDashboardView(List<ShortSaleResp> sales) {
        this.sales = sales;
    }

    public List<ShortSaleResp> getSales() {
        return sales;
    }
}
