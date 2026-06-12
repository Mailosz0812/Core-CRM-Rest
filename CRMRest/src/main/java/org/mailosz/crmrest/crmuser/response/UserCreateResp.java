package org.mailosz.crmrest.crmuser.response;


public class UserCreateResp {
    private String userId;
    private String mail;
    private String name;
    private String surname;
    private String role;

    public UserCreateResp() {
    }

    public UserCreateResp(String userId, String mail, String name, String surname, String role) {
        this.userId = userId;
        this.mail = mail;
        this.name = name;
        this.surname = surname;
        this.role = role;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
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

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
