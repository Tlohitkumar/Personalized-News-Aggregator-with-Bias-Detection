package com.example.news.config;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class JwtFilter implements Filter {

    @Autowired
    private JwtUtil jwtUtil;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;

        String path = req.getRequestURI();

        // ✅ Allow login & register
        if (path.contains("/login") || path.contains("/register")) {
            chain.doFilter(request, response);
            return;
        }

        String header = req.getHeader("Authorization");

        if (header == null || !header.startsWith("Bearer ")) {
            throw new ServletException("Missing Token");
        }

        String token = header.substring(7);

        try {
            jwtUtil.validateToken(token);

            if ("DELETE".equalsIgnoreCase(req.getMethod())) {
                String role = jwtUtil.extractRole(token);
                if (!"ADMIN".equals(role)) {
                    throw new ServletException("Access Denied ❌");
                }
            }

        } catch (Exception e) {
            throw new ServletException("Invalid Token");
        }

        chain.doFilter(request, response);
    }
}