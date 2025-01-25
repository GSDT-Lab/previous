package com.example.springboot.controller;

import com.example.springboot.dto.ChangePasswordRequest;
import com.example.springboot.dto.UserInfoResponse;
import com.example.springboot.dto.UserLoginRequest;
import com.example.springboot.dto.UserRegistrationRequest;
import com.example.springboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    private UserService userService;

    // 注册接口
    @PostMapping("/register")
    public ResponseEntity<Map<String, Object>> register(@RequestBody UserRegistrationRequest request) {
        Map<String, Object> response = userService.register(request);
        if ((Boolean) response.get("success")) {
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }

    // 登录接口
    @PostMapping("/login")
    public ResponseEntity<Map<String, Object>> login(@RequestBody UserLoginRequest request) {
        Map<String, Object> response = userService.login(request);
        if ((Boolean) response.get("success")) {
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
        }
    }

    // 修改密码接口
    @PostMapping("/change-password")
    public ResponseEntity<Map<String, Object>> changePassword(
            @RequestBody ChangePasswordRequest request,
            Authentication authentication) {
        if (authentication == null || !authentication.isAuthenticated()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(Map.of("success", false, "message", "未认证用户"));
        }

        String username = authentication.getName();
        Long userId = userService.getUserIdByUsername(username);
        if (userId == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(Map.of("success", false, "message", "用户不存在"));
        }

        Map<String, Object> response = userService.changePassword(userId, request);
        if ((Boolean) response.get("success")) {
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }

    // 获取个人信息接口
    @GetMapping("/user-info")
    public ResponseEntity<Object> getUserInfo(Authentication authentication) {
        if (authentication == null || !authentication.isAuthenticated()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(Map.of("success", false, "message", "未认证用户"));
        }

        String username = authentication.getName();
        Optional<UserInfoResponse> userInfoOpt = userService.getUserInfo(username);
        if (userInfoOpt.isPresent()) {
            return ResponseEntity.ok(userInfoOpt.get());
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(Map.of("success", false, "message", "用户不存在"));
        }
    }
}
