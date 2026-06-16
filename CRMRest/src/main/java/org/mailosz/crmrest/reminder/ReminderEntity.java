package org.mailosz.crmrest.reminder;

import jakarta.persistence.*;
import org.mailosz.crmrest.crmuser.CrmUserEntity;

import java.time.OffsetDateTime;
import java.util.UUID;

@Entity
@Table(name = "reminders")
public class ReminderEntity {

    @Id
    @GeneratedValue
    @Column(updatable = false)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "user_id",referencedColumnName = "id")
    private CrmUserEntity user;

    private String context;


    private OffsetDateTime reminderAt;

    private boolean isCompleted;

    public ReminderEntity(UUID id, CrmUserEntity user, String context, OffsetDateTime reminderAt, boolean isCompleted) {
        this.id = id;
        this.user = user;
        this.context = context;
        this.reminderAt = reminderAt;
        this.isCompleted = isCompleted;
    }

    public ReminderEntity() {
    }

    public UUID getId() {
        return id;
    }

    public CrmUserEntity getUser() {
        return user;
    }

    public String getContext() {
        return context;
    }

    public OffsetDateTime getReminderAt() {
        return reminderAt;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public void setUser(CrmUserEntity user) {
        this.user = user;
    }

    public void setContext(String context) {
        this.context = context;
    }

    public void setReminderAt(OffsetDateTime reminderAt) {
        this.reminderAt = reminderAt;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    public void setCompleted(boolean completed) {
        isCompleted = completed;
    }
}
