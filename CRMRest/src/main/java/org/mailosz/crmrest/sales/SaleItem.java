package org.mailosz.crmrest.sales;

import jakarta.persistence.*;
import org.mailosz.crmrest.product.ProductEntity;

import java.math.BigDecimal;
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
    @JoinColumn(name = "product_id",referencedColumnName = "id",nullable = false)
    private ProductEntity product;

    @Column(name = "amount",nullable = false,precision = 15, scale = 3)
    private BigDecimal amount;

    @Column(name = "unit_price_at_sale", nullable = false, precision = 15, scale = 3)
    private BigDecimal unitPriceAtSale;

    @Column(name = "sum_price", precision = 15, scale = 2,nullable = false)
    private BigDecimal sumPrice;

    public SaleItem(UUID id, SaleEntity sale, ProductEntity product, BigDecimal amount, BigDecimal unitPriceAtSale, BigDecimal sumPrice) {
        this.id = id;
        this.sale = sale;
        this.product = product;
        this.amount = amount;
        this.unitPriceAtSale = unitPriceAtSale;
        this.sumPrice = sumPrice;
    }

    public SaleItem() {
    }

    public UUID getId() {
        return id;
    }
    public SaleEntity getSale() {
        return sale;
    }

    public void setSale(SaleEntity sale) {
        this.sale = sale;
    }

    public ProductEntity getProduct() {
        return product;
    }

    public void setProduct(ProductEntity product) {
        this.product = product;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public BigDecimal getUnitPriceAtSale() {
        return unitPriceAtSale;
    }

    public void setUnitPriceAtSale(BigDecimal unitPriceAtSale) {
        this.unitPriceAtSale = unitPriceAtSale;
    }

    public BigDecimal getSumPrice() {
        return sumPrice;
    }

    public void setSumPrice(BigDecimal sumPrice) {
        this.sumPrice = sumPrice;
    }
}
