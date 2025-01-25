package com.example.springboot.security;

import com.example.springboot.exception.JWTException;
import com.auth0.jwt.JWT;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.algorithms.Algorithm;
import com.example.springboot.model.User;
import com.example.springboot.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private static final String SECRET_KEY = "yourSecretKey";  // 请使用更复杂的密钥
    private static final String AUTH_HEADER = "Authorization";

    @Autowired
    private UserRepository userRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        String authorizationHeader = request.getHeader(AUTH_HEADER);

        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            String token = authorizationHeader.substring(7);  // 获取Token

            try {
                String username = JWT.require(Algorithm.HMAC256(SECRET_KEY))
                        .build()
                        .verify(token)
                        .getSubject();

                if (username != null) {
                    // 根据用户名从数据库中获取用户信息
                    User user = userRepository.findByUsername(username).orElse(null);
                    if (user != null) {
                        // 创建认证对象
                        UsernamePasswordAuthenticationToken authentication =
                                new UsernamePasswordAuthenticationToken(user.getUsername(), null, null);
                        // 设置认证对象到 SecurityContext
                        SecurityContextHolder.getContext().setAuthentication(authentication);
                    }
                }
            } catch (JWTVerificationException exception) {
                // Token 验证失败
                response.setStatus(HttpStatus.FORBIDDEN.value());
                response.getWriter().write("Token 验证失败");
                return;
            } catch (Exception exception) {
                // 其他异常
                response.setStatus(HttpStatus.FORBIDDEN.value());
                response.getWriter().write("其他错误");
                return;
            }
        }

        // 放行请求
        filterChain.doFilter(request, response);
    }
}
