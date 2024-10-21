package com.security.spring_app.services;

import java.time.Instant;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;

import com.security.spring_app.models.services.IJwkService;

@Service
public class JwkService implements IJwkService {

    @Autowired
    private JwtEncoder jwtEncoder;    

    @Value("${jwk.issuer}")
    private String issuer;

    @Value("${jwk.duration}")
    private long duration;

    @Override
    public String encode(Authentication authentication) {
        String username = authentication.getName();

        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        
        List<String> scopes = authorities.stream().map(GrantedAuthority::getAuthority).toList();

        Instant now = Instant.now();

        JwtClaimsSet claims = JwtClaimsSet
        .builder()
            .issuer(issuer)
            .issuedAt(now)
            .expiresAt(now.plusSeconds(duration))
            .claim("username", username)
            .claim("scope", scopes)
        .build();

        return jwtEncoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();
    }
    
}
