package org.mailosz.crmrest.product;

import jakarta.persistence.*;
import org.mailosz.crmrest.prices.PriceListEntity;
import org.mailosz.crmrest.prices.SellingUnit;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
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
    private SellingUnit unit;

    @Column(nullable = false)
    private OffsetDateTime tps;

    @Column(nullable = false)
    private String producer;

    private String pack;


    public ProductEntity(UUID id, BigDecimal unitPrice, String productName, String internalName,
                         ProductState productState, Boolean visibility,
                         PriceListEntity priceList, SellingUnit unit, OffsetDateTime tps, String producer, String pack) {
        this.id = id;
        this.unitPrice = unitPrice;
        this.productName = productName;
        this.internalName = internalName;
        this.productState = productState;
        this.visibility = visibility;
        this.priceList = priceList;
        this.unit = unit;
        this.tps = tps;
        this.producer = producer;
        this.pack = pack;
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

    public OffsetDateTime getTps() {
        return tps;
    }

    public void setTps(OffsetDateTime tps) {
        this.tps = tps;
    }

    public String getProducer() {
        return producer;
    }

    public void setProducer(String producer) {
        this.producer = producer;
    }

    public String getPack() {
        return pack;
    }

    public void setPack(String pack) {
        this.pack = pack;
    }
}
