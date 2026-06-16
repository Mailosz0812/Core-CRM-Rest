package org.mailosz.crmrest.exception.types;

public class ReminderNotFoundException extends EntityNotFoundException {
    public ReminderNotFoundException(String reminderId) {
        super(reminderId,"REMINDER_NOT_FOUND");
    }
}
