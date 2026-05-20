package org.mailosz.crmrest.prices;

import org.mailosz.crmrest.product.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface PriceListRepository extends JpaRepository<PriceListEntity, UUID> {
    Optional<PriceListEntity> findPriceListEntityById(UUID id);

    @Query("SELECT prod FROM PriceListEntity pr " +
            "JOIN pr.products prod " +
            "WHERE pr.id = :id " +
            "ORDER BY prod.category.name ASC, prod.producer ASC, prod.producer ASC")
    List<ProductEntity> findProductsToPrint(@Param("id") UUID id);

}
