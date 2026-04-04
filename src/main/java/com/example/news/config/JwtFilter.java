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

        if (path.startsWith("/api/users/login") || 
        	    path.startsWith("/api/users/register")) {
        	    chain.doFilter(request, response);
        	    return;
        	}

        String header = req.getHeader("Authorization");

        if (header != null && header.startsWith("Bearer ")) {
            String token = header.substring(7);

            try {
                jwtUtil.validateToken(token); // we will create this
            } catch (Exception e) {
                throw new ServletException("Invalid Token");
            }
        } else {
            throw new ServletException("Missing Token");
        }

        chain.doFilter(request, response);
    }
}