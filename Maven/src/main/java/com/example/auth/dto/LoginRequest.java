// src/main/java/com/example/auth/dto/LoginRequest.java
package com.example.auth.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginRequest {
    private String username;
    private String password;
}
