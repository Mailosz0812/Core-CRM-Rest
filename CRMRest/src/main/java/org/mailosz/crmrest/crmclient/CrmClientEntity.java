package org.mailosz.crmrest.crmclient;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "clients")
public class CrmClientEntity {
    @Id
    @GeneratedValue
    @Column(updatable = false)
    private UUID id;

    @Column(name = "client_name", nullable = false)
    private String name;

    @Column(name = "nip", nullable = false)
    private String nipNumber;

    @Column(name = "address")
    private String address;

    @Column(name = "phone")
    private String phone;

    @Column(name = "mail")
    private String mail;

    @Column(name = "decisionPerson")
    private String decisionPerson;

    public CrmClientEntity() {
    }

    public CrmClientEntity(UUID id, String name, String nipNumber, String address, String phone, String mail, String decisionPerson) {
        this.id = id;
        this.name = name;
        this.nipNumber = nipNumber;
        this.address = address;
        this.phone = phone;
        this.mail = mail;
        this.decisionPerson = decisionPerson;
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNipNumber() {
        return nipNumber;
    }

    public void setNipNumber(String nipNumber) {
        this.nipNumber = nipNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getDecisionPerson() {
        return decisionPerson;
    }

    public void setDecisionPerson(String decisionPerson) {
        this.decisionPerson = decisionPerson;
    }
}
