package org.mailosz.crmrest.stats;

import java.math.BigDecimal;

public interface StatsHeaderValueProjection {
    BigDecimal getIncomeSum();
    BigDecimal getAvgOrderValue();
}
