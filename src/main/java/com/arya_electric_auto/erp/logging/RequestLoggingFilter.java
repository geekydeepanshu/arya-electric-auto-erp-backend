package com.arya_electric_auto.erp.logging;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.io.IOException;

@Component
public class RequestLoggingFilter extends OncePerRequestFilter {

    private static final Logger log = LoggerFactory.getLogger(RequestLoggingFilter.class);

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException {

        long startTime = System.currentTimeMillis();

        String method = request.getMethod();
        String uri = request.getRequestURI();

        filterChain.doFilter(request, response);

        long duration = System.currentTimeMillis() - startTime;

        int status = response.getStatus();
        
        
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        String username = (auth != null && auth.isAuthenticated())
                ? auth.getName()
                : "anonymous";

        log.info("User: {} | {} {} | Status: {} | Time: {} ms",
                username, method, uri, status, duration);
    }
}