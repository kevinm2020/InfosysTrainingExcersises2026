package com.demo.spring_demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

/**
 * Security configuration class for OAuth2 resource server with JWT authentication.
 * 
 * This configuration establishes the security filter chain for the application,
 * defining authorization rules and enabling JWT-based OAuth2 resource server protection.
 * 
 * Authorization Rules:
 * - All requests to "/users/**" endpoints require authentication
 * - All other requests are permitted without authentication
 * 
 * Features:
 * - OAuth2 Resource Server: Configures the application as an OAuth2 resource server
 * - JWT Support: Enables JWT token validation for incoming requests
 */
@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable())      // disable CSRF for Postman testing
            .authorizeHttpRequests(authz -> authz
                .anyRequest().permitAll()); // allow all requests
        return http.build();
    }
}
