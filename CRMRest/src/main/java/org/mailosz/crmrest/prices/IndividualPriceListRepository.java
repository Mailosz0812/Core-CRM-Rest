package org.mailosz.crmrest.prices;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface IndividualPriceListRepository extends JpaRepository<IndividualPriceList, UUID> {
    List<IndividualPriceList> findPriceListEntitiesByClient_Id(UUID id, Pageable pageable);
}
