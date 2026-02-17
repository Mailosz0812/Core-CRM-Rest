package org.mailosz.crmrest.product;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Table(name = "products")
public class ProductEntity {
    @Id
    @GeneratedValue
    @Column(updatable = false)
    private UUID id;

    @Column(name = "prod_name")
    private String name;

    @Column(name = "unit_price",precision = 15, scale = 2)
    private BigDecimal unitPrice;

    @Column(name = "category")
    @Enumerated(EnumType.STRING)
    private Category category;

    public ProductEntity(UUID id, String name, BigDecimal unitPrice, Category category) {
        this.id = id;
        this.name = name;
        this.unitPrice = unitPrice;
        this.category = category;
    }

    public ProductEntity() {
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
