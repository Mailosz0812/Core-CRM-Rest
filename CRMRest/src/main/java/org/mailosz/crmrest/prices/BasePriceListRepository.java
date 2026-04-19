package org.mailosz.crmrest.prices;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface BasePriceListRepository extends JpaRepository<BasePriceList, UUID> {
    Optional<BasePriceList> findFirst();
}
