// src/main/java/com/example/auth/entity/User.java
package com.example.auth.entity;

import javax.persistence.*;
import lombok.*;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable=false, unique=true, length=50)
    private String username;

    @Column(nullable=false, length=100)
    private String password;

    // 其他用户信息字段
}
