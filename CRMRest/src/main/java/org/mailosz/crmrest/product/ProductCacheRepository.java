package org.mailosz.crmrest.product;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ProductCacheRepository extends JpaRepository<ProductEntity, UUID> {
    Optional<ProductEntity> findProductEntityById(UUID id);
}
