package org.mailosz.crmrest.product;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, UUID> {
    List<ProductEntity> findAll();
    Optional<ProductEntity> findProductEntityByProductName(String name);
    Optional<ProductEntity> findProductEntityById(UUID id);

    @Query("SELECT DISTINCT p.producer FROM ProductEntity p WHERE p.producer IS NOT NULL")
    List<String> findDistinctProducers();
}
