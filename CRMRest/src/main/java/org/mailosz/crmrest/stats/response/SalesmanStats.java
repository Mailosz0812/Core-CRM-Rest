package org.mailosz.crmrest.stats.response;

public class SalesmanStats {
    private String generatedRevenue;
    private String target;
    private String wonOffers;
    private String lostOffers;
    private String avgOrderValue;

    public SalesmanStats(String generatedRevenue, String target,
                         String wonOffers, String lostOffers, String avgOrderValue) {
        this.generatedRevenue = generatedRevenue;
        this.target = target;
        this.wonOffers = wonOffers;
        this.lostOffers = lostOffers;
        this.avgOrderValue = avgOrderValue;
    }

    public String getGeneratedRevenue() {
        return generatedRevenue;
    }

    public String getTarget() {
        return target;
    }

    public String getWonOffers() {
        return wonOffers;
    }

    public String getLostOffers() {
        return lostOffers;
    }

    public String getAvgOrderValue() {
        return avgOrderValue;
    }
}
