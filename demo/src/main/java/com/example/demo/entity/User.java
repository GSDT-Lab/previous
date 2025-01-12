package com.example.demo.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "server") // 指定表名为 server
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "username", nullable = false, unique = true)
    private String username;

    @Column(name = "password", nullable = false)
    private String password;

    // 如果需要存储 token，可以添加对应的字段
    // 但通常情况下，JWT token 是不需要存储在数据库中的
    // 如果确实需要，可以取消注释以下代码
    /*
    @Column(name = "token")
    private String token;
    */

    // 无参构造器
    public User() {}

    // 有参构造器
    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    // Getters 和 Setters
    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    // 如果添加了 token 字段，添加对应的 getter 和 setter
    /*
    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
    */
}
