package org.mailosz.crmrest.crmuser.roles;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "crm_roles")
public class RoleEntity {
    @Id
    @GeneratedValue
    private UUID id;

    @Column(name = "role_name", nullable = false)
    private String name;

    public RoleEntity(UUID id, String name) {
        this.id = id;
        this.name = name;
    }
    public RoleEntity() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
