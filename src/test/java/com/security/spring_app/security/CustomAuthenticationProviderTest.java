package com.security.spring_app.security;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;

import java.util.Optional;
import java.util.logging.Logger;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.security.spring_app.dto.UsersDTO;
import com.security.spring_app.entity.Persons;
import com.security.spring_app.entity.Users;
import com.security.spring_app.repository.repositories.UserRepository;
import com.security.spring_app.reuse.UsersTest;

@SpringBootTest
public class CustomAuthenticationProviderTest {

    @MockBean
    private UserRepository userRepository;

    @Autowired
    private CustomAuthenticationProvider customAuthenticationProvider;

    @Autowired
    private PasswordEncoder passwordEncoder;

    private Users users;
    private UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken;

    @BeforeEach 
    void setup () {

        users = UsersTest.getUser();

        usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
            "user",
            "password"
        );
    }

    @Test
    void testAuthenticate() {
        // GIVEN
        given(userRepository.findByUsername("user")).willReturn(Optional.of(users));

        // WHEN
        Authentication authentication = customAuthenticationProvider.authenticate(usernamePasswordAuthenticationToken);

        UsersDTO usersDTO = (UsersDTO) authentication.getDetails();
        System.out.println(authentication);
        // THEN
        assertNotNull(authentication);
        assertEquals(users.getUsername(), authentication.getName());
    }

    @Test
    void testAuthenticatePasswordNotMatches() {
        // GIVEN
        given(userRepository.findByUsername("user")).willReturn(Optional.of(users));

        // WHEN && THEN
        usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
            "user",
            "test"
        );

        assertThrows(BadCredentialsException.class, () -> {
            customAuthenticationProvider.authenticate(usernamePasswordAuthenticationToken);
        });
    }
}
