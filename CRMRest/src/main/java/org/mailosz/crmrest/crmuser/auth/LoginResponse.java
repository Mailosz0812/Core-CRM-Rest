package org.mailosz.crmrest.crmuser.auth;


import org.mailosz.crmrest.crmuser.response.UserResponse;

public class LoginResponse {
    private String token;
    private Long expiresIn;
    private UserResponse userInfo;

    public LoginResponse() {}

    public LoginResponse(String token, Long expiresIn, UserResponse userResponse) {
        this.token = token;
        this.expiresIn = expiresIn;
        this.userInfo = userResponse;
    }

    public String getToken() {
        return token;
    }

    public Long getExpiresIn() {
        return expiresIn;
    }

    public UserResponse getUserInfo() {
        return userInfo;
    }
}
