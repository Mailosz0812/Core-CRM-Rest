package org.mailosz.crmrest.crmclient;

import org.hibernate.validator.constraints.UUID;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/client/view")
public class ClientViewController {
    private ClientDashboardFacade clientFacade;

    public ClientViewController(ClientDashboardFacade clientFacade) {
        this.clientFacade = clientFacade;
    }

    @GetMapping("/{id}")
    public ClientDashboardView getClientDashboardData(@PathVariable @UUID String id){
        return this.clientFacade.getClientDashboardData(id);
    }
}
