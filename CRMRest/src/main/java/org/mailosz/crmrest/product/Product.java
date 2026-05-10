package org.mailosz.crmrest.product;

import org.mailosz.crmrest.prices.SellingUnit;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

public class Product {
    private String id;
    private String name;
    private BigDecimal unitPrice;
    private SellingUnit unit;
    private String internal;
    private OffsetDateTime tps;
    private String producer;
    private String pack;


    public Product(String id, String name, BigDecimal unitPrice, SellingUnit unit,
                   String internal, OffsetDateTime tps, String producer, String pack) {
        this.id = id;
        this.name = name;
        this.unitPrice = unitPrice;
        this.unit = unit;
        this.internal = internal;
        this.tps = tps;
        this.producer = producer;
        this.pack = pack;
    }

    public Product() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
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

    public SellingUnit getUnit() {
        return unit;
    }

    public void setUnit(SellingUnit unit) {
        this.unit = unit;
    }


    public String getInternal() {
        return internal;
    }

    public void setInternal(String internal) {
        this.internal = internal;
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
