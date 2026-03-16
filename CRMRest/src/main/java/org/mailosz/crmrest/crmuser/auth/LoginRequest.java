package org.mailosz.crmrest.crmuser.auth;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public class LoginRequest {
    @Email
    @NotBlank
    private String mail;
    @NotBlank
    private String password;

    public LoginRequest(String mail, String password) {
        this.mail = mail;
        this.password = password;
    }

    public @Email @NotBlank String getMail() {
        return mail;
    }

    public void setMail(@Email @NotBlank String mail) {
        this.mail = mail;
    }

    public @NotBlank String getPassword() {
        return password;
    }

    public void setPassword(@NotBlank String password) {
        this.password = password;
    }
}
