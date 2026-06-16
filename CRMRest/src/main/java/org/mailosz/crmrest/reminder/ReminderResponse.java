package org.mailosz.crmrest.reminder;


import java.time.OffsetDateTime;
import java.util.UUID;

public class ReminderResponse {
    private UUID reminderId;

    private String context;

    private OffsetDateTime reminderAt;

    private boolean isCompleted;

    public ReminderResponse(UUID reminderId, String context, OffsetDateTime reminderAt, boolean isCompleted) {
        this.reminderId = reminderId;
        this.context = context;
        this.reminderAt = reminderAt;
        this.isCompleted = isCompleted;
    }

    public UUID getReminderId() {
        return reminderId;
    }

    public void setReminderId(UUID reminderId) {
        this.reminderId = reminderId;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }

    public OffsetDateTime getReminderAt() {
        return reminderAt;
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
