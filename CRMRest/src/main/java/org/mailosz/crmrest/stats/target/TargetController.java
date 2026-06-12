package org.mailosz.crmrest.stats.target;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/stats/target")
public class TargetController {
    private final TargetService targetService;

    public TargetController(TargetService targetService) {
        this.targetService = targetService;
    }

    @PostMapping
    public TargetResponse createTarget(@RequestBody @Valid TargetRequest req){
        return this.targetService.createTarget(req);
    }
    @PutMapping
    public TargetResponse modifyTarget(@RequestBody @Valid TargetRequest req){
        return this.targetService.modifyTarget(req);
    }
}
