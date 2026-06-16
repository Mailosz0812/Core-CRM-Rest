package org.mailosz.crmrest.sales;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface SaleStageRepository extends JpaRepository<SaleStage, UUID> {
    Optional<SaleStage> findSaleStageByStage(Stage stage);
}
