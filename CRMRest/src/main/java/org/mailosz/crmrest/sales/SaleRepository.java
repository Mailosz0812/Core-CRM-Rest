package org.mailosz.crmrest.sales;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface SaleRepository extends JpaRepository<SaleEntity, UUID> {
    Optional<SaleEntity> findSaleEntityById(UUID id);
    List<SaleEntity> findSaleEntitiesByClientId(UUID id, Pageable pageable);
}
