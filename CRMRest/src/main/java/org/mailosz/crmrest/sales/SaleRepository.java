package org.mailosz.crmrest.sales;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;


import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface SaleRepository extends JpaRepository<SaleEntity, UUID>, JpaSpecificationExecutor<SaleEntity> {
    Optional<SaleEntity> findSaleEntityById(UUID id);
    List<SaleEntity> findSaleEntitiesByClientId(UUID id, Pageable pageable);
    List<SaleEntity> findAllByUser_Mail(String name, Pageable pageable);
    List<SaleEntity> findSaleEntitiesBySaleDateBeforeAndStage_Stage(OffsetDateTime day, Stage stage);
}
