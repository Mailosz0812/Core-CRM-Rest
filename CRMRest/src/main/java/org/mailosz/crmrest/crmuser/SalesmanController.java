package org.mailosz.crmrest.crmuser;

import jakarta.validation.Valid;
import org.mailosz.crmrest.crmuser.request.UserCreateReq;
import org.mailosz.crmrest.crmuser.response.UserCreateResp;
import org.mailosz.crmrest.crmuser.roles.Role;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/users/salesman")
public class SalesmanController {

    private final UserService userService;

    public SalesmanController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<UserCreateResp> createSalesman(@RequestBody @Valid UserCreateReq req) {
        UserCreateResp resp = this.userService.createUser(req, Role.SALESMAN.name());
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(resp.getUserId())
                .toUri();
        return ResponseEntity.created(location).body(resp);
    }

}
