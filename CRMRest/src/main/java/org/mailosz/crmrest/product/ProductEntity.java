package org.mailosz.crmrest.product;

import jakarta.persistence.*;
import org.mailosz.crmrest.crmclient.CrmClientEntity;
import org.mailosz.crmrest.prices.PriceListEntity;
import org.mailosz.crmrest.prices.SellingUnit;

import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Table(name = "products")
public class ProductEntity {
    @Id
    @GeneratedValue
    @Column(updatable = false)
    private UUID id;

    @Column(name = "unit_price",precision = 15, scale = 2,nullable = false)
    private BigDecimal unitPrice;

    @Column(name="prod_name",nullable = false)
    private String productName;

    @Column(name = "internal_name", nullable = false)
    private String internalName;

    @ManyToOne
    @JoinColumn(name = "cache_id", referencedColumnName = "id")
    private ProductState productState;

    @Column(nullable = false)
    private Boolean visibility;

    @ManyToOne
    @JoinColumn(name = "price_list_id", referencedColumnName = "id", nullable = false)
    private PriceListEntity priceList;

    @Enumerated(EnumType.STRING)
    private Category category;

    @Enumerated(EnumType.STRING)
    private SellingUnit unit;


    public ProductEntity(UUID id, BigDecimal unitPrice, String productName, String internalName, ProductState productState, Boolean visibility, PriceListEntity priceList, Category category, SellingUnit unit) {
        this.id = id;
        this.unitPrice = unitPrice;
        this.productName = productName;
        this.internalName = internalName;
        this.productState = productState;
        this.visibility = visibility;
        this.priceList = priceList;
        this.category = category;
        this.unit = unit;
    }

    public ProductEntity() {
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public ProductState getProductState() {
        return productState;
    }

    public void setProductState(ProductState productState) {
        this.productState = productState;
    }

    public Boolean getVisibility() {
        return visibility;
    }

    public void setVisibility(Boolean visibility) {
        this.visibility = visibility;
    }

    public PriceListEntity getPriceList() {
        return priceList;
    }

    public void setPriceList(PriceListEntity priceList) {
        this.priceList = priceList;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public SellingUnit getUnit() {
        return unit;
    }

    public void setUnit(SellingUnit unit) {
        this.unit = unit;
    }

    public String getInternalName() {
        return internalName;
    }

    public void setInternalName(String internalName) {
        this.internalName = internalName;
    }
}
