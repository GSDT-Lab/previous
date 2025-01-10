// src/main/java/com/example/auth/dto/JwtResponse.java
package com.example.auth.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class JwtResponse {
    private String token;
}
