package com.example.demo.service;

import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    // 注册用户
    public User registerUser(String username, String password) throws Exception {
        if (userRepository.findByUsername(username) != null) {
            throw new Exception("用户名已存在");
        }
        String encodedPassword = passwordEncoder.encode(password);
        User user = new User(username, encodedPassword);
        return userRepository.save(user);
    }

    // 验证用户登录
    public User authenticate(String username, String password) throws Exception {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new Exception("用户不存在");
        }
        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new Exception("密码不正确");
        }
        return user;
    }

    // 根据用户名获取用户
    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }
}
