package org.mailosz.crmrest.crmclient;

public class ClientResponse {
    private String id;
    private String name;
    private String nipNumber;
    private String address;
    private String phone;
    private String mail;
    private String decisionPerson;

    public ClientResponse(String id, String name,
                          String nipNumber, String address,
                          String phone, String mail, String decisionPerson) {
        this.id = id;
        this.name = name;
        this.nipNumber = nipNumber;
        this.address = address;
        this.phone = phone;
        this.mail = mail;
        this.decisionPerson = decisionPerson;
    }
    public ClientResponse(){}

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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
