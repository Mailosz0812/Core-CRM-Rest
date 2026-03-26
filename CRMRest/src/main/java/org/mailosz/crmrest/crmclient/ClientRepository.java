package org.mailosz.crmrest.crmclient;

import org.mailosz.crmrest.crmuser.CrmUserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ClientRepository extends JpaRepository<CrmClientEntity, UUID> {
    Optional<CrmClientEntity> findCrmClientEntityById(UUID id);
    Optional<CrmClientEntity> findCrmClientEntityByNipNumber(String nipNumber);
}
