package org.mailosz.crmrest.reminder;

import org.mailosz.crmrest.crmuser.CrmUserEntity;
import org.mailosz.crmrest.crmuser.UserRepository;
import org.mailosz.crmrest.exception.types.CrmUserNotFoundException;
import org.mailosz.crmrest.exception.types.ReminderNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class ReminderService {

    private final ReminderRepository reminderRepo;
    private final UserRepository userRepo;

    public ReminderService(UserRepository userRepo, ReminderRepository reminderRepo) {
        this.userRepo = userRepo;
        this.reminderRepo = reminderRepo;
    }

    public ReminderResponse createReminder(ReminderCreationReq req, String username){
        CrmUserEntity user = this.userRepo.findCrmUserEntityByMail(username)
                .orElseThrow(() -> new CrmUserNotFoundException(username));

        ReminderEntity reminder = new ReminderEntity();

        reminder.setContext(req.getContext());
        reminder.setReminderAt(req.getReminderAt());
        reminder.setUser(user);

        ReminderEntity saved = this.reminderRepo.save(reminder);
        return new ReminderResponse(saved.getId(),saved.getContext(),saved.getReminderAt(),saved.isCompleted());
    }

    public List<ReminderResponse> getDailyReminders(String username){
        CrmUserEntity user = this.userRepo.findCrmUserEntityByMail(username)
                .orElseThrow(() -> new CrmUserNotFoundException(username));

        OffsetDateTime endOfTheDay = OffsetDateTime.now().with(LocalTime.MAX);

        return this.reminderRepo.findDailyReminders(endOfTheDay,user).stream()
                .map(reminder -> new ReminderResponse(
                        reminder.getId(),reminder.getContext(),
                        reminder.getReminderAt(), reminder.isCompleted()))
                .toList();
    }
    public ReminderResponse checkReminder(UUID reminderId){
        ReminderEntity reminder = this.reminderRepo.findReminderEntityById(reminderId)
                .orElseThrow(() -> new ReminderNotFoundException(reminderId.toString()));

        reminder.setCompleted(true);
        ReminderEntity saved = this.reminderRepo.save(reminder);
        return new ReminderResponse(saved.getId(),saved.getContext(),saved.getReminderAt(),saved.isCompleted());
    }
}
