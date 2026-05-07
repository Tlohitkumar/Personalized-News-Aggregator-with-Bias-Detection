package com.example.news.config;

import java.io.IOException;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
public class JwtFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain)
            throws ServletException, IOException {

        // Get API path
        String path = request.getRequestURI();

        // Public APIs (No Token Needed)
        if (path.startsWith("/api/news")
                || path.startsWith("/api/users")) {

            filterChain.doFilter(request, response);
            return;
        }

        // Protected APIs
        String token = request.getHeader("Authorization");

        // Missing Token
        if (token == null || token.isEmpty()) {

            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);

            response.getWriter().write("Missing Token");

            return;
        }

        // Continue request
        filterChain.doFilter(request, response);
    }
}