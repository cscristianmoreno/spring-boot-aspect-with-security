package com.security.spring_app.jwk;

import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.time.Duration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.core.DelegatingOAuth2TokenValidator;
import org.springframework.security.oauth2.core.OAuth2TokenValidator;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtIssuerValidator;
import org.springframework.security.oauth2.jwt.JwtTimestampValidator;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtEncoder;

import com.nimbusds.jose.jwk.JWK;
import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jose.jwk.source.ImmutableJWKSet;
import com.nimbusds.jose.jwk.source.JWKSource;
import com.nimbusds.jose.proc.SecurityContext;

@Configuration
public class CustomJwkConfig {
    
    @Value("${jwk.private.key}")
    public RSAPrivateKey privateKey;

    @Value("${jwk.public.key}")
    private RSAPublicKey publicKey;

    @Value("${jwk.issuer}")
    private String issuer;

    @Bean
    JwtEncoder jwtEncoder() {
        JWK jwk = new RSAKey.Builder(publicKey).privateKey(privateKey).build();
        JWKSet jwkSet = new JWKSet(jwk);
        JWKSource<SecurityContext> immutableJWKSet = new ImmutableJWKSet<SecurityContext>(jwkSet);
        NimbusJwtEncoder nimbusJwtEncoder = new NimbusJwtEncoder(immutableJWKSet);
        return nimbusJwtEncoder;
    }

    @Bean
    JwtDecoder jwtDecoder() {
        OAuth2TokenValidator<Jwt> oAuth2TokenValidator = new DelegatingOAuth2TokenValidator<>(
            new JwtTimestampValidator(Duration.ofSeconds(60)),
            new JwtIssuerValidator(issuer)
        );

        NimbusJwtDecoder nimbusJwtDecoder = NimbusJwtDecoder.withPublicKey(publicKey).build();
        nimbusJwtDecoder.setJwtValidator(oAuth2TokenValidator);
        return nimbusJwtDecoder;
    }
}
