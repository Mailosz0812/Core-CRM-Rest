package org.mailosz.crmrest.prices;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.mailosz.crmrest.crmclient.CrmClientEntity;
import org.mailosz.crmrest.product.ProductEntity;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "price_list")
public class PriceListEntity {
    @Id
    @GeneratedValue
    @Column(updatable = false)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "client_id", referencedColumnName = "id",nullable = false)
    private CrmClientEntity client;

    @Column(nullable = false)
    private String title;

    @CreationTimestamp
    private OffsetDateTime createdAt;

    @OneToMany(mappedBy = "priceList", cascade = CascadeType.ALL,orphanRemoval = true)
    private List<ProductEntity> products;

    public PriceListEntity(UUID id, CrmClientEntity client, String title,
                           OffsetDateTime createdAt, List<ProductEntity> products) {
        this.id = id;
        this.client = client;
        this.title = title;
        this.createdAt = createdAt;
        this.products = products;
    }

    public PriceListEntity() {
    }

    public UUID getId() {
        return id;
    }

    public CrmClientEntity getClient() {
        return client;
    }

    public String getTitle() {
        return title;
    }

    public OffsetDateTime getCreatedAt() {
        return createdAt;
    }

    public List<ProductEntity> getProducts() {
        return products;
    }

    public void setClient(CrmClientEntity client) {
        this.client = client;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setProducts(List<ProductEntity> products) {
        this.products = products;
    }

    @Override
    public String toString() {
        return "PriceListEntity{" +
                "id=" + id +
                ", client=" + client +
                ", title='" + title + '\'' +
                ", createdAt=" + createdAt +
                ", products=" + products +
                '}';
    }
}
