package org.mailosz.crmrest.sales;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.mailosz.crmrest.crmclient.CrmClientEntity;
import org.mailosz.crmrest.crmuser.CrmUserEntity;

import java.time.OffsetDateTime;
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

    @CreationTimestamp
    private OffsetDateTime createdAt;

    @UpdateTimestamp
    private OffsetDateTime updatedAt;
    private OffsetDateTime checkedAt;

    public SaleEntity(UUID id, CrmClientEntity client, CrmUserEntity user, SaleStage stage, OffsetDateTime createdAt, OffsetDateTime updatedAt, OffsetDateTime checkedAt) {
        this.id = id;
        this.client = client;
        this.user = user;
        this.stage = stage;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.checkedAt = checkedAt;
    }

    public SaleEntity() {
    }

    public UUID getId() {
        return id;
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

    public SaleStage getStage() {
        return stage;
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
