package org.mailosz.crmrest.crmuser;

import org.mailosz.crmrest.crmuser.response.SalesmanDashboardView;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/users/salesman")
public class SalesmanController {

    private final SalesmanViewFacade salesmanFacade;

    public SalesmanController(SalesmanViewFacade salesmanFacade) {
        this.salesmanFacade = salesmanFacade;
    }
    @GetMapping("/view")
    public SalesmanDashboardView getSalesmanDashboardInfo(@AuthenticationPrincipal String username){
        return this.salesmanFacade.getSalesmanDashboardInfo(username);
    }

}
