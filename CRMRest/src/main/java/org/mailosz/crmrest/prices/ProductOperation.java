package org.mailosz.crmrest.prices;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.mailosz.crmrest.prices.request.ProductDeleteOperation;
import org.mailosz.crmrest.prices.request.ProductUpdateOperation;
import org.mailosz.crmrest.prices.request.ProductUpdateReq;

@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.EXISTING_PROPERTY,
        property = "operationType",
        visible = true
)

@JsonSubTypes({
        @JsonSubTypes.Type(value = ProductUpdateOperation.class, name = "UPDATE"),
        @JsonSubTypes.Type(value = ProductDeleteOperation.class, name = "DELETE")
})
public class ProductOperation {

    @NotNull
    private OperationType operationType;

    public ProductOperation(OperationType operationType) {
        this.operationType = operationType;
    }

    public @NotNull OperationType getOperationType() {
        return operationType;
    }
}
