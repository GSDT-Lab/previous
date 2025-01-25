package com.example.springboot.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.example.springboot.dto.ChangePasswordRequest;
import com.example.springboot.dto.UserInfoResponse;
import com.example.springboot.dto.UserLoginRequest;
import com.example.springboot.dto.UserRegistrationRequest;
import com.example.springboot.exception.JWTException;
import com.example.springboot.model.User;
import com.example.springboot.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    // 添加 PasswordEncoder 以加密密码
    @Autowired
    private PasswordEncoder passwordEncoder;

    private static final String SECRET_KEY = "yourSecretKey"; // 请使用更复杂的密钥

    // 注册方法
    public Map<String, Object> register(UserRegistrationRequest request) {
        Map<String, Object> result = new HashMap<>();

        // 校验输入
        if (request.getUsername() == null || request.getPhoneNumber() == null ||
                request.getBirthDate() == null || request.getPassword() == null ||
                request.getHobbies() == null || request.getGender() == null ||
                request.getLocation() == null ||
                request.getUsername().isEmpty() || request.getPhoneNumber().isEmpty() ||
                request.getPassword().isEmpty()) {
            result.put("success", false);
            result.put("message", "所有字段都是必填的");
            return result;
        }

        // 检查用户名和手机号是否已存在
        if (userRepository.findByUsername(request.getUsername()).isPresent()) {
            result.put("success", false);
            result.put("message", "用户名已存在");
            return result;
        }

        if (userRepository.findByPhoneNumber(request.getPhoneNumber()).isPresent()) {
            result.put("success", false);
            result.put("message", "手机号已注册");
            return result;
        }

        // 加密密码
        String encodedPassword = passwordEncoder.encode(request.getPassword());

        User newUser = new User(
                request.getUsername(),
                request.getPhoneNumber(),
                request.getBirthDate(),
                encodedPassword,
                request.getHobbies(),
                request.getGender(),
                request.getLocation()
        );
        newUser.setRegistrationTime(new Date());
        userRepository.save(newUser);

        result.put("success", true);
        result.put("message", "注册成功");
        return result;
    }

    // 登录方法
    public Map<String, Object> login(UserLoginRequest request) {
        Map<String, Object> result = new HashMap<>();

        String account = request.getAccount();
        String password = request.getPassword();

        if (account == null || password == null || account.isEmpty() || password.isEmpty()) {
            result.put("success", false);
            result.put("message", "账号和密码不能为空");
            return result;
        }

        Optional<User> userOpt = userRepository.findByUsername(account);
        if (!userOpt.isPresent()) {
            userOpt = userRepository.findByPhoneNumber(account);
        }

        if (userOpt.isPresent()) {
            User user = userOpt.get();
            // 验证密码
            if (passwordEncoder.matches(password, user.getPassword())) {
                String token = generateToken(user); // 生成Token

                result.put("success", true);
                result.put("message", "登录成功");
                result.put("token", token);
                result.put("username", user.getUsername());
                result.put("userID", user.getId());
                result.put("birthDate", user.getBirthDate());
                result.put("phoneNumber", user.getPhoneNumber());
                result.put("hobbies", user.getHobbies());
                result.put("gender", user.getGender());
                result.put("location", user.getLocation());
                result.put("registrationTime", user.getRegistrationTime());
                return result;
            } else {
                result.put("success", false);
                result.put("message", "密码错误");
                return result;
            }
        } else {
            result.put("success", false);
            result.put("message", "账号不存在");
            return result;
        }
    }

    // 生成JWT Token
    private String generateToken(User user) {
        return JWT.create()
                .withSubject(user.getUsername())
                .withClaim("userID", user.getId())
                .withIssuedAt(new Date())
                .withExpiresAt(new Date(System.currentTimeMillis() + 86400000)) // 设置24小时有效期
                .sign(Algorithm.HMAC256(SECRET_KEY));
    }

    // 修改密码方法
    public Map<String, Object> changePassword(Long userId, ChangePasswordRequest request) {
        Map<String, Object> result = new HashMap<>();

        if (request.getOriginalPassword() == null || request.getNewPassword() == null ||
                request.getOriginalPassword().isEmpty() || request.getNewPassword().isEmpty()) {
            result.put("success", false);
            result.put("message", "原密码和新密码不能为空");
            return result;
        }

        Optional<User> userOpt = userRepository.findById(userId);
        if (userOpt.isPresent()) {
            User user = userOpt.get();
            // 验证原密码
            if (passwordEncoder.matches(request.getOriginalPassword(), user.getPassword())) {
                // 更新密码
                user.setPassword(passwordEncoder.encode(request.getNewPassword()));
                userRepository.save(user);

                result.put("success", true);
                result.put("message", "密码修改成功");
                return result;
            } else {
                result.put("success", false);
                result.put("message", "原密码不正确");
                return result;
            }
        } else {
            result.put("success", false);
            result.put("message", "用户不存在");
            return result;
        }
    }
    // 在 UserService 类中添加
    public Long getUserIdByUsername(String username) {
        return userRepository.findByUsername(username)
                .map(User::getId)
                .orElse(null);
    }

    // 获取用户信息方法
    public Optional<UserInfoResponse> getUserInfo(String username) {
        Optional<User> userOpt = userRepository.findByUsername(username);
        if (userOpt.isPresent()) {
            User user = userOpt.get();
            UserInfoResponse userInfo = new UserInfoResponse();
            userInfo.setId(user.getId());
            userInfo.setUsername(user.getUsername());
            userInfo.setPhoneNumber(user.getPhoneNumber());
            userInfo.setBirthDate(user.getBirthDate());
            userInfo.setHobbies(user.getHobbies());
            userInfo.setGender(user.getGender());
            userInfo.setLocation(user.getLocation());
            userInfo.setRegistrationTime(user.getRegistrationTime());
            return Optional.of(userInfo);
        }
        return Optional.empty();
    }
}
