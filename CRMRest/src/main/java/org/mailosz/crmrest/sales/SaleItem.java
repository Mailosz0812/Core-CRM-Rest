package org.mailosz.crmrest.sales;

import jakarta.persistence.*;
import org.mailosz.crmrest.product.ProductState;

import java.math.BigDecimal;
import java.math.RoundingMode;
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
    private ProductState product;

    @Column(name = "product_name",nullable = false)
    private String name;

    @Column(name = "amount",nullable = false,precision = 15, scale = 2)
    private BigDecimal amount;

    @Column(name = "unit_price_at_sale", nullable = false, precision = 15, scale = 2)
    private BigDecimal unitPriceAtSale;

    @Column(name = "sum_price", precision = 15, scale = 2,nullable = false)
    private BigDecimal sumPrice;

    public SaleItem(UUID id, SaleEntity sale, ProductState product,
                    String name, BigDecimal amount, BigDecimal unitPriceAtSale) {
        this.id = id;
        this.sale = sale;
        this.product = product;
        this.name = name;
        this.amount = amount;
        this.unitPriceAtSale = unitPriceAtSale;
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

    public ProductState getProduct() {
        return product;
    }

    public void setProduct(ProductState product) {
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
        return sumPrice;
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
