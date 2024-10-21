package com.security.spring_app.security;

import java.io.IOException;
import java.util.Map;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ServerProperties.Jetty.Threads;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.security.spring_app.services.UserRepositoryService;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class CustomOncePerRequestFilter extends OncePerRequestFilter {

    @Autowired
    private JwtDecoder jwtDecoder;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws IOException, ServletException {

        String uri = request.getRequestURI();

        if (uri.contains("/login") || uri.contains("/users/save") || uri.contains("/actuator")) {
            filterChain.doFilter(request, response);
            return;
        }
        
        String header = request.getHeader(HttpHeaders.AUTHORIZATION);

        if (header == null || !header.startsWith("Bearer ")) {
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            response.getWriter().write("Header is empty or contain is malformed!");
            return;
        }

        String authorization = header.split(" ")[1];

        try {

            Map<String, Object> claims = jwtDecoder.decode(authorization).getClaims();
            Object scope = claims.get("scope");

            String username = (String) claims.get("username");

            @SuppressWarnings("unchecked")
            List<String> authorities = (List<String>) scope;

            Authentication authentication = new UsernamePasswordAuthenticationToken(
                username,
                null,
                AuthorityUtils.createAuthorityList(authorities)
            );

            SecurityContext securityContext = SecurityContextHolder.getContext();
            securityContext.setAuthentication(authentication);

            filterChain.doFilter(request, response);
        }
        catch (Exception e) {
            response.setStatus(HttpStatus.CONFLICT.value());
            response.getWriter().write(e.getMessage());
        }
    }
}
