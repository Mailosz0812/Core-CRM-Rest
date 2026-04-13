package org.mailosz.crmrest.sales.request;

import jakarta.validation.constraints.NotNull;
import org.mailosz.crmrest.sales.Stage;

import java.time.OffsetDateTime;
import java.util.UUID;

public class StageOperationReq {

    @NotNull
    private UUID saleId;

    @NotNull
    private Stage stage;

    private OffsetDateTime packageDate;

    public StageOperationReq(UUID saleId, Stage stage, OffsetDateTime packageDate) {
        this.saleId = saleId;
        this.stage = stage;
        this.packageDate = packageDate;
    }

    public StageOperationReq() {
    }

    public Stage getStage() {
        return stage;
    }

    public OffsetDateTime getPackageDate() {
        return packageDate;
    }

    public UUID getSaleId() {
        return saleId;
    }

    @Override
    public String toString() {
        return "StageOperationReq{" +
                "saleId=" + saleId +
                ", stage=" + stage +
                ", packageDate=" + packageDate +
                '}';
    }
}
