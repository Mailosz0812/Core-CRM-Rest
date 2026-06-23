package org.mailosz.crmrest.reminder;

import org.mailosz.crmrest.crmuser.CrmUserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ReminderRepository extends JpaRepository<ReminderEntity, UUID> {

    @Query("SELECT r FROM ReminderEntity r WHERE r.isCompleted = false AND r.reminderAt <= :reminderAt AND r.user = :user")
    List<ReminderEntity> findDailyReminders(@Param("reminderAt") OffsetDateTime reminderAt, @Param("user") CrmUserEntity user);

    Optional<ReminderEntity> findReminderEntityById(UUID id);

}
