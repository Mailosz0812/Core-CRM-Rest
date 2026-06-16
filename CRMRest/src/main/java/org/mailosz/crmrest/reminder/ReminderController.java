package org.mailosz.crmrest.reminder;

import jakarta.validation.Valid;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/reminder")
public class ReminderController {

    private final ReminderService reminderService;

    public ReminderController(ReminderService reminderService) {
        this.reminderService = reminderService;
    }

    @PostMapping
    public ReminderResponse createReminder(@RequestBody @Valid ReminderCreationReq req, @AuthenticationPrincipal String username){
        return this.reminderService.createReminder(req,username);
    }

    @GetMapping("/daily")
    public List<ReminderResponse> getDailyReminders(@AuthenticationPrincipal String username){
        return this.reminderService.getDailyReminders(username);
    }

    @PostMapping("/{id}")
    public ReminderResponse checkReminder(@PathVariable UUID id){
        return this.reminderService.checkReminder(id);
    }
}
