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

    @OneToMany(mappedBy = "sale",orphanRemoval = true,cascade = CascadeType.ALL)
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

    @Column(name= "warehouse_note")
    private String warehouseNote;

    @Column(name = "sale_date")
    private OffsetDateTime saleDate;

    @Column(name = "sale_name", nullable = false)
    private String saleName;

    public SaleEntity(UUID id, CrmClientEntity client, CrmUserEntity user, SaleStage stage, List<SaleItem> saleItems,
                      OffsetDateTime createdAt, OffsetDateTime updatedAt,
                      OffsetDateTime checkedAt, BigDecimal sumPrice, String saleData,
                      String warehouseNote, OffsetDateTime saleDate, String saleName) {
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
        this.warehouseNote = warehouseNote;
        this.saleDate = saleDate;
        this.saleName = saleName;
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

    public Stage getStage() {
        return stage.getStage();
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

    public String getWarehouseNote() {
        return warehouseNote;
    }

    public void setWarehouseNote(String warehouseNote) {
        this.warehouseNote = warehouseNote;
    }

    public OffsetDateTime getSaleDate() {
        return saleDate;
    }

    public void setSaleDate(OffsetDateTime saleDate) {
        this.saleDate = saleDate;
    }

    public String getSaleName() {
        return saleName;
    }

    public void setSaleName(String saleName) {
        this.saleName = saleName;
    }
}
