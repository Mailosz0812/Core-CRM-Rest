package org.mailosz.crmrest.prices;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import org.mailosz.crmrest.product.ProductEntity;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@DiscriminatorValue("BASE")
public class BasePriceList extends PriceListEntity{
    public BasePriceList(UUID id, String title, OffsetDateTime createdAt, List<ProductEntity> products) {
        super(id, title, createdAt, products);
    }

    public BasePriceList() {
    }
}
