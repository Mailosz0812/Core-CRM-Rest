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

    @Column(name = "external_id", nullable = false, updatable = false)
    private String externalId;

    @Column(name = "product_state", nullable = false,precision = 15,scale = 3)
    private BigDecimal productState;

    @UpdateTimestamp
    private OffsetDateTime lastUpdate;

    @OneToOne
    @JoinColumn(name = "product_id",referencedColumnName = "id",nullable = false)
    private ProductEntity product;

    public ProductState(UUID id, String externalId, BigDecimal productState, OffsetDateTime lastUpdate, ProductEntity product) {
        this.id = id;
        this.externalId = externalId;
        this.productState = productState;
        this.lastUpdate = lastUpdate;
        this.product = product;
    }

    public ProductState() {
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

    public ProductEntity getProduct() {
        return product;
    }

    public void setProduct(ProductEntity product) {
        this.product = product;
    }

    public UUID getId() {
        return id;
    }
}

