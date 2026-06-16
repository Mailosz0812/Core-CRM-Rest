package org.mailosz.crmrest.crmuser;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<CrmUserEntity, UUID> {
    Optional<CrmUserEntity> findCrmUserEntityById(UUID id);
    Optional<CrmUserEntity> findCrmUserEntityByMail(String mail);

    @Query("SELECT u FROM CrmUserEntity u " +
            "JOIN u.role role " +
            "WHERE role.name = 'SALESMAN' OR role.name = 'MAGAZINE'")
    List<CrmUserEntity> findAllNonAdminUsers();
}
