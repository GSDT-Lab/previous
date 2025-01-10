// src/main/java/com/example/auth/dto/RegisterRequest.java
package com.example.auth.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegisterRequest {
    private String username;
    private String password;
    // 其他注册需要的字段
}
