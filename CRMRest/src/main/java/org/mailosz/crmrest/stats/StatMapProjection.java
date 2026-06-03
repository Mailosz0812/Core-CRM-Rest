package org.mailosz.crmrest.stats;

import java.math.BigDecimal;

public interface StatMapProjection {

    String getMonthName();
    BigDecimal getCalcValue();
}
