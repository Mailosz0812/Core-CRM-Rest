package org.mailosz.crmrest.stats.target;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.util.UUID;

public class TargetRequest {

    @NotNull
    private UUID userId;

    @DecimalMin(value = "0.00",message = "Target should be greater than zero")
    private BigDecimal target;

    public TargetRequest(UUID userId, BigDecimal target) {
        this.userId = userId;
        this.target = target;
    }

    public UUID getUserId() {
        return userId;
    }

    public BigDecimal getTarget() {
        return target;
    }
}
