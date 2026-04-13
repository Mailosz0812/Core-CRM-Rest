package org.mailosz.crmrest.crmuser;

import jakarta.validation.Valid;
import org.mailosz.crmrest.crmuser.request.UserCreateReq;
import org.mailosz.crmrest.crmuser.response.SalesmanDashboardView;
import org.mailosz.crmrest.crmuser.response.UserCreateResp;
import org.mailosz.crmrest.crmuser.roles.Role;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/users/salesman")
public class SalesmanController {

    private final UserService userService;
    private final SalesmanViewFacade salesmanFacade;

    public SalesmanController(UserService userService, SalesmanViewFacade salesmanFacade) {
        this.userService = userService;
        this.salesmanFacade = salesmanFacade;
    }

    @PostMapping
    public ResponseEntity<UserCreateResp> createSalesman(@RequestBody @Valid UserCreateReq req) {
        UserCreateResp resp = this.userService.createUser(req, Role.SALESMAN.name());
        URI location = ServletUriComponentsBuilder
                .fromPath("/users/admin")
                .path("/{id}")
                .buildAndExpand(resp.getUserId())
                .toUri();
        return ResponseEntity.created(location).body(resp);
    }

    @GetMapping("/view")
    public SalesmanDashboardView getSalesmanDashboardInfo(@AuthenticationPrincipal String username){
        return this.salesmanFacade.getSalesmanDashboardInfo(username);
    }

}
