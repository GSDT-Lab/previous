package com.example.springboot.exception;

public class JWTException extends Exception {

    // 传递错误消息给父类构造函数
    public JWTException(String message) {
        super(message);
    }

    // 传递错误消息和异常原因给父类构造函数
    public JWTException(String message, Throwable cause) {
        super(message, cause);
    }
}
