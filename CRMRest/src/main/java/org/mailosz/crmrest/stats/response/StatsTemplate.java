package org.mailosz.crmrest.stats.response;

import java.math.BigDecimal;
import java.util.Map;

public class StatsTemplate {
    private String headerValue;
    private Map<String, BigDecimal> monthsMap;
    private BigDecimal difference;

    public StatsTemplate(String headerValue, Map<String, BigDecimal> monthsMap, BigDecimal difference) {
        this.headerValue = headerValue;
        this.monthsMap = monthsMap;
        this.difference = difference;
    }

    public Map<String, BigDecimal> getMonthsMap() {
        return monthsMap;
    }

    public BigDecimal getDifference() {
        return difference;
    }


    public void setMonthsMap(Map<String, BigDecimal> monthsMap) {
        this.monthsMap = monthsMap;
    }

    public void setDifference(BigDecimal difference) {
        this.difference = difference;
    }

    public String getHeaderValue() {
        return headerValue;
    }
}
