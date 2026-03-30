package org.mailosz.crmrest.crmclient;

import org.mailosz.crmrest.crmclient.response.ClientDashboardView;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/client/view")
public class ClientViewController {
    private ClientDashboardFacade clientFacade;

    public ClientViewController(ClientDashboardFacade clientFacade) {
        this.clientFacade = clientFacade;
    }

    @GetMapping("/{id}")
    public ClientDashboardView getClientDashboardData(@PathVariable UUID id){
        return this.clientFacade.getClientDashboardData(id);
    }
}
