package org.mailosz.crmrest.crmclient.response;

public class ClientWidgetResponse {
    private String name;
    private String nipNumber;
    private String address;
    private String phone;

    public ClientWidgetResponse(String name, String nipNumber, String address, String phone) {
        this.name = name;
        this.nipNumber = nipNumber;
        this.address = address;
        this.phone = phone;
    }

    public ClientWidgetResponse() {
    }

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

    public void setName(String name) {
        this.name = name;
    }

    public void setNipNumber(String nipNumber) {
        this.nipNumber = nipNumber;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}

