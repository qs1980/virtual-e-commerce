package org.example.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .csrf().disable()   // 禁用 CSRF（如果是 API 接口）
            .authorizeHttpRequests(auth -> auth
                .anyRequest().permitAll()  // 所有请求都放行，不做认证
            );
        return http.build();
    }
}
