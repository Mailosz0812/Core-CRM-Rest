package org.mailosz.crmrest.product;

import jakarta.persistence.*;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.UUID;

@Entity
@Table(name = "products_cache")
public class ProductState {

    @Id
    @GeneratedValue
    @Column(updatable = false)
    private UUID id;

    @Column(name = "product_name",nullable = false,updatable = false)
    private String name;

    @Column(name = "external_id", nullable = false, updatable = false)
    private String externalId;

    @Column(name = "product_state", nullable = false,precision = 15,scale = 3)
    private BigDecimal productState;

    @UpdateTimestamp
    private OffsetDateTime lastUpdate;

    public ProductState(UUID id, String name, String externalId, BigDecimal productState, OffsetDateTime lastUpdate) {
        this.id = id;
        this.name = name;
        this.externalId = externalId;
        this.productState = productState;
        this.lastUpdate = lastUpdate;
    }

    public ProductState() {
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getExternalId() {
        return externalId;
    }

    public void setExternalId(String externalId) {
        this.externalId = externalId;
    }

    public BigDecimal getProductState() {
        return productState;
    }

    public void setProductState(BigDecimal productState) {
        this.productState = productState;
    }

    public OffsetDateTime getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(OffsetDateTime lastUpdate) {
        this.lastUpdate = lastUpdate;
    }
}

