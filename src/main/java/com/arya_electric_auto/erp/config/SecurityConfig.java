package com.arya_electric_auto.erp.config;

import com.arya_electric_auto.erp.logging.RequestLoggingFilter;
import com.arya_electric_auto.erp.security.JwtFilter;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@EnableMethodSecurity
@Configuration
public class SecurityConfig {

    private final JwtFilter jwtFilter;
    private final RequestLoggingFilter requestLoggingFilter;

    public SecurityConfig(JwtFilter jwtFilter, RequestLoggingFilter requestLoggingFilter) {
        this.jwtFilter = jwtFilter;
        this.requestLoggingFilter = requestLoggingFilter;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http
            .csrf(csrf -> csrf.disable())
            .authorizeHttpRequests(auth -> auth
                    .requestMatchers("/api/auth/**").permitAll()
                    .anyRequest().authenticated()
            )
            .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)
        	.addFilterAfter(requestLoggingFilter, JwtFilter.class);
        

        return http.build();
    }
}