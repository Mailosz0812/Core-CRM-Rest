package org.mailosz.crmrest.crmclient.request;

import jakarta.validation.constraints.*;
import org.hibernate.validator.constraints.pl.NIP;

public class ClientRequest {

    @NotBlank
    @Size(max = 50)
    private String name;

    @NotBlank
    @NIP
    private String nipNumber;

    @NotBlank
    @Size(max = 100)
    private String address;

    @NotBlank
    @Pattern(regexp = "^\\d+$")
    @Size(min = 9, max = 9)
    private String phone;


    @Email
    @NotBlank
    @Size(max = 50)
    private String mail;

    @Size(max=120)
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
