// src/main/java/com/example/auth/controller/AuthController.java
package com.example.auth.controller;

import com.example.auth.dto.*;
import com.example.auth.entity.User;
import com.example.auth.security.JwtTokenUtil;
import com.example.auth.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.security.authentication.*;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.core.Authentication;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserService userService;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    // 注册接口
    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody RegisterRequest registerRequest) {
        try {
            User user = userService.registerUser(registerRequest.getUsername(), registerRequest.getPassword());
            return ResponseEntity.ok("User registered successfully");
        } catch(Exception e) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(e.getMessage());
        }
    }

    // 登录接口
    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@RequestBody LoginRequest loginRequest) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginRequest.getUsername(),
                            loginRequest.getPassword()
                    )
            );

            final String token = jwtTokenUtil.generateToken(loginRequest.getUsername());
            return ResponseEntity.ok(new JwtResponse(token));

        } catch (BadCredentialsException e) {
            return ResponseEntity
                    .status(HttpStatus.UNAUTHORIZED)
                    .body("用户名或密码错误");
        } catch (AuthenticationException e) {
            return ResponseEntity
                    .status(HttpStatus.UNAUTHORIZED)
                    .body("认证失败");
        }
    }

    // 示例受保护的接口
    @GetMapping("/profile")
    public ResponseEntity<?> getUserProfile(Authentication authentication) {
        User user = (User) authentication.getPrincipal();
        // 仅返回用户名作为示例
        return ResponseEntity.ok("Welcome, " + user.getUsername());
    }
}
