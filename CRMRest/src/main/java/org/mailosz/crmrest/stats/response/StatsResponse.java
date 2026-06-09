package org.mailosz.crmrest.stats.response;

public class StatsResponse {
    private StatsTemplate incomeStats;
    private StatsTemplate orderVolume;

    public StatsResponse(StatsTemplate incomeStats, StatsTemplate orderVolume) {
        this.incomeStats = incomeStats;
        this.orderVolume = orderVolume;
    }

    public StatsTemplate getIncomeStats() {
        return incomeStats;
    }

    public StatsTemplate getOrderVolume() {
        return orderVolume;
    }

    public void setIncomeStats(StatsTemplate incomeStats) {
        this.incomeStats = incomeStats;
    }

    public void setOrderVolume(StatsTemplate orderVolume) {
        this.orderVolume = orderVolume;
    }
}
