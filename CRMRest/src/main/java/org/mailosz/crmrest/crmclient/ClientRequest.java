package org.mailosz.crmrest.crmclient;

import jakarta.validation.constraints.*;
import org.hibernate.validator.constraints.pl.NIP;

public class ClientRequest {

    @NotBlank
    private String name;

    @NotBlank
    @NIP
    private String nipNumber;

    @NotBlank
    private String address;

    @NotBlank
    @Pattern(regexp = "^\\d+$")
    private String phone;


    @Email
    @NotBlank
    private String mail;
    private String decisionPerson;

    public ClientRequest(String name, String nipNumber, String address,
                         String phone, String mail, String decisionPerson) {
        this.name = name;
        this.nipNumber = nipNumber;
        this.address = address;
        this.phone = phone;
        this.mail = mail;
        this.decisionPerson = decisionPerson;
    }
    public ClientRequest(){}

    public String getName() {
        return name;
    }

    public String getNipNumber() {
        return nipNumber;
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
