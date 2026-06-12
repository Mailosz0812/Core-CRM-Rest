package org.mailosz.crmrest.crmuser;

import org.mailosz.crmrest.crmuser.request.UserCreateReq;
import org.mailosz.crmrest.crmuser.response.UserCreateResp;
import org.mailosz.crmrest.crmuser.roles.RoleService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;
    private final RoleService roleService;

    public UserController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping("/{id}")
    public UserCreateResp getUser(@PathVariable UUID id){
        return this.userService.getUserById(id);
    }
    @PostMapping
    public ResponseEntity<UserCreateResp> createUser(@RequestBody UserCreateReq req){
        UserCreateResp resp = this.userService.createUser(req);
        URI location = ServletUriComponentsBuilder
                .fromPath("/users")
                .path("/{id}")
                .buildAndExpand(resp.getUserId())
                .toUri();
        return ResponseEntity.created(location).body(resp);
    }

    @GetMapping("/roles")
    public List<String> getRoles(){
        return this.roleService.getRoles();
    }

    @DeleteMapping("/{id}")
    public UUID deleteUser(@PathVariable UUID id){
        return this.userService.deleteUser(id);
    }
    @GetMapping
    public List<UserCreateResp> getAllUsers(){
        return this.userService.getAllUsers();
    }


}
