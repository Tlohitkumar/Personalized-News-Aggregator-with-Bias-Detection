package com.example.news.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http
            .csrf(csrf -> csrf.disable())

            .authorizeHttpRequests(auth -> auth

                // ✅ Public APIs
                .requestMatchers("/api/users/**").permitAll()
                .requestMatchers("/api/news/**").permitAll()

                // 🔒 Protected APIs
                .requestMatchers("/api/favorites/**").authenticated()

                // everything else
                .anyRequest().permitAll()
            );

        return http.build();
    }
}
