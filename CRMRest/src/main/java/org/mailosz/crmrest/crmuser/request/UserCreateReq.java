package org.mailosz.crmrest.crmuser.request;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.mailosz.crmrest.helpers.validator.ValidPassword;

@ValidPassword
public class UserCreateReq {

    @Email
    private String mail;

    private String password;
    private String confirmPassword;

    @NotBlank
    private String name;
    @NotNull
    private String surname;

    public UserCreateReq(String mail, String password, String confirmPassword, String name, String surname) {
        this.mail = mail;
        this.password = password;
        this.confirmPassword = confirmPassword;
        this.name = name;
        this.surname = surname;
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

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
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
