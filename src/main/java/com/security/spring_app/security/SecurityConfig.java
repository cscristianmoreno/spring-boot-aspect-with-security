package com.security.spring_app.security;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableMethodSecurity(prePostEnabled = true)
public class SecurityConfig {

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return (
            httpSecurity
                .authorizeHttpRequests((req) -> {
                    req.requestMatchers(
                        "/users/save", "/auth/login",
                        "/swagger-ui/**",
                        "/v1/**",
                        "/api/**",
                        "/actuator/**"
                    ).permitAll();
                    req.anyRequest().authenticated();
                })
                .sessionManagement((session) -> {
                    session.sessionCreationPolicy(SessionCreationPolicy.NEVER);
                })
                .csrf((csrf) -> {
                    csrf.disable();
                })
                .httpBasic((basic) -> {
                    basic.disable();
                })
            .build()
        );
    }
}
