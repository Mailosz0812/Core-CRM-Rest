package org.mailosz.crmrest.crmclient.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import org.hibernate.validator.constraints.UUID;

public class ClientUpdateReq {

    @UUID
    @NotBlank
    private String clientId;

    @NotBlank
    private String name;

    @NotBlank
    private String address;

    @NotBlank
    @Pattern(regexp = "^\\d+$")
    @Size(min = 9, max = 9)
    private String phone;

    @Email
    @NotBlank
    private String mail;
    private String decisionPerson;

    public ClientUpdateReq(String clientId, String name, String address,
                           String phone, String mail, String decisionPerson) {
        this.clientId = clientId;
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.mail = mail;
        this.decisionPerson = decisionPerson;
    }

    public ClientUpdateReq() {
    }

    public String getClientId() {
        return clientId;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getPhone() {
        return phone;
    }

    public String getMail() {
        return mail;
    }

    public String getDecisionPerson() {
        return decisionPerson;
    }
}
