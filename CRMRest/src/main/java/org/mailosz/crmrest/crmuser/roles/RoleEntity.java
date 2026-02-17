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
}
