package com.example.news.config;

import jakarta.servlet.*;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

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
        HttpServletResponse res = (HttpServletResponse) response;
        
     // ✅ CORS headers
        res.setHeader("Access-Control-Allow-Origin", "*");
        res.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
        res.setHeader("Access-Control-Allow-Headers", "*");

     // ✅ Allow OPTIONS (CORS preflight)
        if ("OPTIONS".equalsIgnoreCase(req.getMethod())) {
            chain.doFilter(request, response);
            return;
        }
        
        String path = req.getRequestURI();
        
     
        // ✅ Allow login & register
        if (path.contains("/login") || path.contains("/register") || path.contains("/news")) {
            chain.doFilter(request, response);
            return;
        }

        String header = req.getHeader("Authorization");

        if (header == null || !header.startsWith("Bearer ")) {
        	 res.setStatus(HttpServletResponse.SC_UNAUTHORIZED); // 401
             res.getWriter().write("Missing Token");
             return;
        }

        String token = header.substring(7);

        try {
            jwtUtil.validateToken(token);

            if ("DELETE".equalsIgnoreCase(req.getMethod())) {
                String role = jwtUtil.extractRole(token);
                if (!"ADMIN".equals(role)) {
                	 res.setStatus(HttpServletResponse.SC_FORBIDDEN); // 403
                     res.getWriter().write("Access Denied ❌");
                     return;
                }
            }

        } catch (Exception e) {
        	res.setStatus(HttpServletResponse.SC_UNAUTHORIZED); // 401
            res.getWriter().write("Invalid Token");
            return;
        }

        chain.doFilter(request, response);
    }
}