package com.security.spring_app.security;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import static org.mockito.BDDMockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.security.spring_app.reuse.AuthCompleteTest;

@SpringBootTest
public class CustomAuthenticationManagerTest {

    @Autowired
    private CustomAuthenticationManager customAuthenticationManager;
    
    @Autowired
    private PasswordEncoder passwordEncoder;

    @MockBean 
    private CustomAuthenticationProvider customAuthenticationProvider;


    private UsernamePasswordAuthenticationToken authLogin;
    private UsernamePasswordAuthenticationToken authComplete;

    @BeforeEach
    void setup() {
        authLogin = new UsernamePasswordAuthenticationToken(
            "user",
            "password"
        );

        authComplete = AuthCompleteTest.getAuth();
    }

    @Test
    void testAuthenticate() {

        // GIVEN
        given(customAuthenticationProvider.authenticate(authLogin)).willReturn(authComplete);

        // WHEN
        Authentication authentication = customAuthenticationManager.authenticate(authLogin);

        // THEN
        assertNotNull(authentication);
        assertEquals(authentication, authComplete);
    }

    @Test
    void testAuthenticatePasswordFailed() {

        // GIVEN
        given(customAuthenticationProvider.authenticate(authLogin)).willThrow(new BadCredentialsException("username not found"));

        // WHEN && THEN
        assertThrows(BadCredentialsException.class, () -> {
            customAuthenticationManager.authenticate(authLogin);
        });
    }
}
