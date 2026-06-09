package org.mailosz.crmrest.stats.target;

import org.mailosz.crmrest.crmuser.CrmUserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

public interface TargetRepository extends JpaRepository<TargetEntity, UUID> {

    Optional<TargetEntity> findTargetEntityByUserAndTargetMonth(CrmUserEntity user, LocalDate month);

}
