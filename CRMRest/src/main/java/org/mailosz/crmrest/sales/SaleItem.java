package org.mailosz.crmrest.sales;

import jakarta.persistence.*;
import org.mailosz.crmrest.prices.SellingUnit;
import org.mailosz.crmrest.product.ProductEntity;
import org.mailosz.crmrest.product.ProductState;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.OffsetDateTime;
import java.util.UUID;

@Entity
@Table(name = "sales_item")
public class SaleItem {
    @Id
    @GeneratedValue
    @Column(updatable = false)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "sale_id",referencedColumnName = "id",nullable = false)
    private SaleEntity sale;

    @ManyToOne
    @JoinColumn(name = "product_id",referencedColumnName = "id")
    private ProductEntity product;

    @Column(name = "product_name",nullable = false)
    private String name;

    @Column(name = "internal_name", nullable = false)
    private String internalName;

    @Column(name = "amount",nullable = false,precision = 15, scale = 2)
    private BigDecimal amount;

    @Column(name = "unit_price_at_sale", nullable = false, precision = 15, scale = 2)
    private BigDecimal unitPriceAtSale;

    @Column(name = "sum_price", precision = 15, scale = 2,nullable = false)
    private BigDecimal sumPrice;

    @Enumerated(value = EnumType.STRING)
    private SellingUnit unit;

    private OffsetDateTime tps;

    public SaleItem(UUID id, SaleEntity sale, ProductEntity product, String name, String internalName,
                    BigDecimal amount, BigDecimal unitPriceAtSale, BigDecimal sumPrice, SellingUnit unit, OffsetDateTime tps) {
        this.id = id;
        this.sale = sale;
        this.product = product;
        this.name = name;
        this.internalName = internalName;
        this.amount = amount;
        this.unitPriceAtSale = unitPriceAtSale;
        this.sumPrice = sumPrice;
        this.unit = unit;
        this.tps = tps;
    }

    public SaleItem() {
    }

    public UUID getId() {
        return id;
    }
    public SaleEntity getSale() {
        return sale;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSale(SaleEntity sale) {
        this.sale = sale;
    }

    public ProductEntity getProduct() {
        return product;
    }

    public void setProduct(ProductEntity product) {
        this.product = product;
        calculateSum();
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
        calculateSum();
    }

    public BigDecimal getUnitPriceAtSale() {
        return unitPriceAtSale;
    }

    public void setUnitPriceAtSale(BigDecimal unitPriceAtSale) {
        this.unitPriceAtSale = unitPriceAtSale;
        calculateSum();
    }

    public BigDecimal getSumPrice() {
        calculateSum();
        return sumPrice;
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

    @PrePersist
    @PreUpdate
    private void calculateSum(){
        if(this.amount != null && this.unitPriceAtSale != null){
            BigDecimal multiply = this.amount.multiply(this.unitPriceAtSale);
            this.sumPrice = multiply.setScale(2, RoundingMode.HALF_UP);
        }else{
            this.sumPrice = BigDecimal.ZERO;
        }
    }
}
