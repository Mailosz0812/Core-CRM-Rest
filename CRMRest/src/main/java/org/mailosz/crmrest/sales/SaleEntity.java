package org.mailosz.crmrest.sales;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.mailosz.crmrest.crmclient.CrmClientEntity;
import org.mailosz.crmrest.crmuser.CrmUserEntity;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "sales")
public class SaleEntity {

    @Id
    @GeneratedValue
    @Column(updatable = false)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "client_id", referencedColumnName = "id", nullable = false)
    private CrmClientEntity client;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id",nullable = false)
    private CrmUserEntity user;

    @ManyToOne
    @JoinColumn(name = "stage_id", referencedColumnName = "id",nullable = false)
    private SaleStage stage;

    @OneToMany(mappedBy = "sale")
    private List<SaleItem> saleItems;

    @CreationTimestamp
    private OffsetDateTime createdAt;

    @UpdateTimestamp
    private OffsetDateTime updatedAt;
    private OffsetDateTime checkedAt;

    @Column(name = "sum_price")
    private BigDecimal sumPrice;

    @Column(name = "sale_data")
    private String saleData;

    public SaleEntity(UUID id, CrmClientEntity client, CrmUserEntity user, SaleStage stage,
                      List<SaleItem> saleItems, OffsetDateTime createdAt,
                      OffsetDateTime updatedAt, OffsetDateTime checkedAt, BigDecimal sumPrice, String saleData) {
        this.id = id;
        this.client = client;
        this.user = user;
        this.stage = stage;
        this.saleItems = saleItems;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.checkedAt = checkedAt;
        this.sumPrice = sumPrice;
        this.saleData = saleData;
    }

    public SaleEntity() {
    }

    public UUID getId() {
        return id;
    }

    public String getSaleData() {
        return saleData;
    }

    public BigDecimal getSumPrice() {
        return sumPrice;
    }

    public void setSumPrice(BigDecimal sumPrice) {
        this.sumPrice = sumPrice;
    }

    public void setSaleData(String saleData) {
        this.saleData = saleData;
    }

    public List<SaleItem> getSaleItems() {
        return saleItems;
    }

    public CrmClientEntity getClient() {
        return client;
    }

    public void setClient(CrmClientEntity client) {
        this.client = client;
    }

    public CrmUserEntity getUser() {
        return user;
    }

    public void setUser(CrmUserEntity user) {
        this.user = user;
    }

    public String getStage() {
        return stage.getStage().toString().toLowerCase();
    }

    public void setStage(SaleStage stage) {
        this.stage = stage;
    }

    public OffsetDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(OffsetDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public OffsetDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(OffsetDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public OffsetDateTime getCheckedAt() {
        return checkedAt;
    }

    public void setCheckedAt(OffsetDateTime checkedAt) {
        this.checkedAt = checkedAt;
    }
}
