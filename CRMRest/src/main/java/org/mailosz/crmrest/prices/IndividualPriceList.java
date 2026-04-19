package org.mailosz.crmrest.prices;

import jakarta.persistence.*;
import org.mailosz.crmrest.crmclient.CrmClientEntity;
import org.mailosz.crmrest.product.ProductEntity;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@DiscriminatorValue("INDIVIDUAL")
public class IndividualPriceList extends PriceListEntity{
    @ManyToOne
    @JoinColumn(name = "client_id", referencedColumnName = "id",nullable = false)
    private CrmClientEntity client;

    @Column(nullable = false)
    private String title;

    public IndividualPriceList(UUID id, OffsetDateTime createdAt, List<ProductEntity> products, CrmClientEntity client,String title) {
        super(id, createdAt, products);
        this.client = client;
        this.title = title;
    }

    public IndividualPriceList() {
    }

    public CrmClientEntity getClient() {
        return client;
    }

    public void setClient(CrmClientEntity client) {
        this.client = client;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
