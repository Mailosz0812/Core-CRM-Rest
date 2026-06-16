package org.mailosz.crmrest.reminder;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.time.OffsetDateTime;

public class ReminderCreationReq {

    @NotBlank
    @Size(max = 100)
    private String context;

    @Future(message = "Data przypomnienia musi być w przyszłości")
    private OffsetDateTime reminderAt;

    public ReminderCreationReq(String context, OffsetDateTime reminderAt) {
        this.context = context;
        this.reminderAt = reminderAt;
    }

    public ReminderCreationReq() {
    }

    public @NotBlank String getContext() {
        return context;
    }

    public @Future(message = "Data przypomnienia musi być w przyszłości") OffsetDateTime getReminderAt() {
        return reminderAt;
    }
}
