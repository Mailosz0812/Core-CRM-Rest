package org.mailosz.crmrest.crmuser;


import org.mailosz.crmrest.crmuser.response.AdminDashboardView;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/users/admin")
public class AdminController {
    private final AdminViewFacade adminFacade;

    public AdminController(AdminViewFacade adminFacade) {
        this.adminFacade = adminFacade;
    }

    @GetMapping("/view")
    public AdminDashboardView getAdminDashboardView(){
        return this.adminFacade.getAdminDashboardInfo();
    }

}
