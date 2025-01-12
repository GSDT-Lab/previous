package com.example.springboot.service;

import com.example.springboot.model.User;
import com.example.springboot.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private UserRepository userRepository;

    // 注册方法
    public boolean register(String username, String password) {
        logger.info("尝试注册用户：{}", username);
        Optional<User> existingUser = userRepository.findByUsername(username);
        if (existingUser.isPresent()) {
            logger.warn("注册失败，用户名已存在：{}", username);
            return false; // 用户名已存在
        }
        User newUser = new User(username, password); // 直接存储明文密码
        userRepository.save(newUser);
        logger.info("注册成功，用户名：{}", username);
        return true;
    }

    // 登录方法
    public boolean login(String username, String password) {
        logger.info("用户 {} 尝试登录", username);
        Optional<User> userOpt = userRepository.findByUsername(username);
        if (userOpt.isPresent()) {
            User user = userOpt.get();
            if (user.getPassword().equals(password)) {
                logger.info("用户 {} 登录成功", username);
                return true;
            } else {
                logger.warn("用户 {} 登录失败，密码错误", username);
            }
        } else {
            logger.warn("用户 {} 登录失败，用户名不存在", username);
        }
        return false;
    }
}
