package com.example.springboot.model;

import javax.persistence.*;

@Entity
@Table(name = "users") // 数据表名为 'users'
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    // 构造方法
    public User() {}

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    // Getter 和 Setter
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
}
