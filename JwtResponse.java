package com.chatbotProject.chatbot.config;

public class JwtResponse {
    private String token;
    private boolean is2faRequired;
    private String qrCode;
    private String secretKey;

    // Constructors, getters, and setters
    public JwtResponse(String token, boolean is2faRequired, String qrCode, String secretKey) {
        this.token = token;
        this.is2faRequired = is2faRequired;
        this.qrCode = qrCode;
        this.secretKey = secretKey;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public boolean isIs2faRequired() {
        return is2faRequired;
    }

    public void setIs2faRequired(boolean is2faRequired) {
        this.is2faRequired = is2faRequired;
    }

    public String getQrCode() {
        return qrCode;
    }

    public void setQrCode(String qrCode) {
        this.qrCode = qrCode;
    }

    public String getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }
}


