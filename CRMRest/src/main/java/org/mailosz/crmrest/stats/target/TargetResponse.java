package org.mailosz.crmrest.stats.target;

import java.math.BigDecimal;
import java.time.LocalDate;

public class TargetResponse {

    private String targetId;
    private String userId;
    private LocalDate targetMonth;
    private BigDecimal target;

    public TargetResponse(String targetId, String userId, LocalDate targetMonth, BigDecimal target) {
        this.targetId = targetId;
        this.userId = userId;
        this.targetMonth = targetMonth;
        this.target = target;
    }

    public String getTargetId() {
        return targetId;
    }

    public void setTargetId(String targetId) {
        this.targetId = targetId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public LocalDate getTargetMonth() {
        return targetMonth;
    }

    public void setTargetMonth(LocalDate targetMonth) {
        this.targetMonth = targetMonth;
    }

    public BigDecimal getTarget() {
        return target;
    }

    public void setTarget(BigDecimal target) {
        this.target = target;
    }
}
