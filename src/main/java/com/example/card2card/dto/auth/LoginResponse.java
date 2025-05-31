package com.example.card2card.dto.auth;


/**
 * DTO class used to return the authentication token to the client after successful login.
 */
public class LoginResponse {

    private String token;

    public LoginResponse(String token) {
        this.token = token;
    }

    public LoginResponse() {
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
