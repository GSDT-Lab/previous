package com.example.springboot.dto;

public class UserLoginRequest {
    private String account; // 账号可以是用户名或手机号
    private String password;

    // Getter 和 Setter
    // ...（省略）

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
