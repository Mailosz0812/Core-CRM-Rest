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
@RequestMapping("/users/magazine")
public class MagazineController {
    private final UserService userService;

    public MagazineController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<UserCreateResp> createWarehouseman(@RequestBody @Valid UserCreateReq req){
        UserCreateResp resp = this.userService.createUser(req,Role.MAGAZINE.name());
        URI location = ServletUriComponentsBuilder
                .fromPath("/users/admin")
                .path("/{id}")
                .buildAndExpand(resp.getUserId())
                .toUri();
        return ResponseEntity.created(location).body(resp);
    }

}
