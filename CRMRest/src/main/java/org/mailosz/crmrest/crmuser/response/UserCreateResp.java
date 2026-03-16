package org.mailosz.crmrest.crmuser.response;


public class UserCreateResp {
    private String userId;
    private String mail;
    private String name;
    private String surname;

    public UserCreateResp() {
    }

    public UserCreateResp(String userId, String mail, String name, String surname) {
        this.userId = userId;
        this.mail = mail;
        this.name = name;
        this.surname = surname;
    }

    public String getUserId() {
        return userId;
    }

    public String getMail() {
        return mail;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }
}
