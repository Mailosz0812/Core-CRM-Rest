package org.mailosz.crmrest.crmuser.request;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.mailosz.crmrest.crmuser.roles.Role;
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

    @NotNull
    private Role role;

    public UserCreateReq(String mail, String password, String confirmPassword, String name, String surname, Role role) {
        this.mail = mail;
        this.password = password;
        this.confirmPassword = confirmPassword;
        this.name = name;
        this.surname = surname;
        this.role = role;
    }

    public @Email String getMail() {
        return mail;
    }

    public String getPassword() {
        return password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public @NotBlank String getName() {
        return name;
    }

    public @NotNull String getSurname() {
        return surname;
    }

    public @NotNull Role getRole() {
        return role;
    }
}
