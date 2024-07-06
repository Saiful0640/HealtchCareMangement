package com.doctormangment.config;

public class AuthenticationResponse {
    private String username;
    private String role;
    private String message;

    public AuthenticationResponse() {
    }

    public AuthenticationResponse(String username, String role) {
        this.username = username;
        this.role = role;
    }

    public AuthenticationResponse(String message) {
        this.message = message;
    }

    // Getters and Setters

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
