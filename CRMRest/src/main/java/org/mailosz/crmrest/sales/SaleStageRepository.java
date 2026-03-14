package org.mailosz.crmrest.sales;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface SaleStageRepository extends JpaRepository<SaleStage, UUID> {
    Optional<SaleStage> findSaleStageByStage(Stage stage);
}
