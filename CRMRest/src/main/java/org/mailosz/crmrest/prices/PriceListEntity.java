package org.mailosz.crmrest.prices;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.mailosz.crmrest.product.ProductEntity;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "price_list")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="list_type", discriminatorType = DiscriminatorType.STRING)
public class PriceListEntity {
    @Id
    @GeneratedValue
    @Column(updatable = false)
    private UUID id;

    @CreationTimestamp
    private OffsetDateTime createdAt;

    @OneToMany(mappedBy = "priceList", cascade = CascadeType.ALL,orphanRemoval = true)
    private List<ProductEntity> products;

    public PriceListEntity(UUID id,
                           OffsetDateTime createdAt, List<ProductEntity> products) {
        this.id = id;
        this.createdAt = createdAt;
        this.products = products;
    }

    public PriceListEntity() {
    }

    public UUID getId() {
        return id;
    }


    public OffsetDateTime getCreatedAt() {
        return createdAt;
    }

    public List<ProductEntity> getProducts() {
        return products;
    }


    public void setProducts(List<ProductEntity> products) {
        this.products = products;
    }

}
