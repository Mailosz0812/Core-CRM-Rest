package org.mailosz.crmrest.stats;

import org.mailosz.crmrest.stats.response.SalesmanStats;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/stats")
public class StatsController {
    private final StatsService statsService;

    public StatsController(StatsService statsService) {
        this.statsService = statsService;
    }

    @GetMapping("/{id}")
    public SalesmanStats getSalesmanStats(@PathVariable UUID id){
        return this.statsService.findSalesmanStats(id);
    }
}
