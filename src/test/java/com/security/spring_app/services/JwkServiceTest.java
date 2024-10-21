package com.security.spring_app.services;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootTest
public class JwkServiceTest {

    @Autowired
    private JwkService jwkService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    private UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken;

    @BeforeEach
    void setup() {
        usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
            "user",
            passwordEncoder.encode("user"),
            AuthorityUtils.createAuthorityList("ROLE_USER", "ROLE_ADMIN")
        );
    }

    @Test
    void testEncode() {
        String token = jwkService.encode(usernamePasswordAuthenticationToken);

        assertNotNull(token);
        System.out.println(token);
    }
}
