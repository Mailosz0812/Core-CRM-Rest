package org.mailosz.crmrest.crmuser;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<CrmUserEntity, UUID> {
    Optional<CrmUserEntity> findCrmUserEntityById(UUID id);
}
