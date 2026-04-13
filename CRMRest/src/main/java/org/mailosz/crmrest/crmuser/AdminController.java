package org.mailosz.crmrest.crmuser;


import org.mailosz.crmrest.crmuser.response.AdminDashboardView;
import org.mailosz.crmrest.crmuser.response.UserResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/users/admin")
public class AdminController {
    private final UserService service;
    private final AdminViewFacade adminFacade;

    public AdminController(UserService service, AdminViewFacade adminFacade) {
        this.service = service;
        this.adminFacade = adminFacade;
    }

    @GetMapping("/{id}")
    public UserResponse getUserById(@PathVariable UUID id){
        return this.service.getUserById(id);
    }

    @GetMapping("/view")
    public AdminDashboardView getAdminDashboardView(){
        return this.adminFacade.getAdminDashboardInfo();
    }

}
