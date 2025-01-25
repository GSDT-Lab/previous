package com.example.springboot.dto;

public class ChangePasswordRequest {
    private String originalPassword;
    private String newPassword;

    // Getter 和 Setter
    // ...（省略）

    public String getOriginalPassword() {
        return originalPassword;
    }

    public void setOriginalPassword(String originalPassword) {
        this.originalPassword = originalPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }
}
