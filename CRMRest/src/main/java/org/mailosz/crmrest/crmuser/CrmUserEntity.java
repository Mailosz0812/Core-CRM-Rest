package org.mailosz.crmrest.crmuser;

import jakarta.persistence.*;
import org.mailosz.crmrest.crmuser.roles.RoleEntity;

import java.util.UUID;

@Entity
@Table(name = "crm_users")
public class CrmUserEntity {
    @Id
    @GeneratedValue
    @Column(updatable = false)
    private UUID id;

    @Column(unique = true,nullable = false)
    private String mail;

    @Column(name = "secret_pass",nullable = false)
    private String password;

    @ManyToOne
    @JoinColumn(name = "role_id", referencedColumnName = "id",nullable = false)
    private RoleEntity role;

    @Column(name = "crm_user_name")
    private String name;

    @Column(name = "crm_user_surname")
    private String surname;

    public CrmUserEntity() {
    }

    public CrmUserEntity(UUID id, String mail, String password, RoleEntity role, String name, String surname) {
        this.id = id;
        this.mail = mail;
        this.password = password;
        this.role = role;
        this.name = name;
        this.surname = surname;
    }

    public UUID getId() {
        return id;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public RoleEntity getRole() {
        return role;
    }

    public void setRole(RoleEntity role) {
        this.role = role;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }
}
