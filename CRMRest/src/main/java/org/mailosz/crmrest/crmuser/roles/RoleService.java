package org.mailosz.crmrest.crmuser.roles;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleService {
    private final RoleRepository roleRepo;

    public RoleService(RoleRepository roleRepo) {
        this.roleRepo = roleRepo;
    }

    public List<String> getRoles(){
        return this.roleRepo.findAll().stream().map(RoleEntity::getName).toList();
    }
}
