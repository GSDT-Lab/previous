package com.example.springboot;

import com.example.springboot.security.JwtAuthenticationFilter;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest
public class SpringbootApplicationTests {

    @MockBean
    private JwtAuthenticationFilter jwtAuthenticationFilter;

    @Test
    void contextLoads() {
        // 测试逻辑
    }
}
