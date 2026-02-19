package org.mailosz.crmrest.product;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, UUID> {
    List<ProductEntity> findAll();
    Optional<ProductEntity> findProductEntityByName(String name);
    Optional<ProductEntity> findProductEntityById(UUID id);
    Optional<ProductEntity> findProductEntityByProductStateId(UUID id);
}
