package com.example.springboot.controller;

import com.example.springboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    private UserService userService;

    // 注册接口
    @PostMapping("/register")
    public Map<String, Object> register(@RequestBody Map<String, String> payload) {
        String username = payload.get("username");
        String password = payload.get("password");
        boolean success = userService.register(username, password);
        Map<String, Object> response = new HashMap<>();
        response.put("success", success);
        if (!success) {
            response.put("message", "用户名已存在");
        }
        return response;
    }

    // 登录接口
    @PostMapping("/login")
    public Map<String, Object> login(@RequestBody Map<String, String> payload) {
        String username = payload.get("username");
        String password = payload.get("password");
        boolean success = userService.login(username, password);
        Map<String, Object> response = new HashMap<>();
        response.put("success", success);
        if (!success) {
            response.put("message", "用户名或密码错误");
        }
        return response;
    }
}
