package org.mailosz.crmrest.prices;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface PriceListRepository extends JpaRepository<PriceListEntity, UUID> {
    Optional<PriceListEntity> findPriceListEntityById(UUID id);
    List<PriceListEntity> findPriceListEntitiesByClient_Id(UUID id);

}
