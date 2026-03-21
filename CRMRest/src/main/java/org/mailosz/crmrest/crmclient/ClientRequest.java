package org.mailosz.crmrest.crmclient;

public class ClientRequest {
    private String name;
    private String nipNumber;
    private String address;
    private String phone;
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
