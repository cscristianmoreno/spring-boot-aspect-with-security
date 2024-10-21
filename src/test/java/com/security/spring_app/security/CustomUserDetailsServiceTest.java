package com.security.spring_app.security;


import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.BDDMockito.*;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.security.spring_app.entity.Persons;
import com.security.spring_app.entity.Users;
import com.security.spring_app.repository.repositories.UserRepository;

@SpringBootTest
public class CustomUserDetailsServiceTest {
    
    @MockBean
    private UserRepository userRepository;

    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    private Users users;

    @BeforeEach
    void setup() {
        Persons persons = new Persons();
        persons.setName("Cristian");
        persons.setLastname("Moreno");
        persons.setAge(27);
        persons.setDni(40148758);

        users = new Users();
        users.setId(1);
        users.setUsername("user");
        users.setPassword(passwordEncoder.encode("user"));
        users.setEmail("cristianmorenoweb@gmail.com");
        users.setPersons(persons);
    }

    @Test
    void testLoadUserByUsername() {

        // GIVEN
        given(userRepository.findByUsername("user")).willReturn(Optional.of(users));

        // WHEN
        Optional<Users> result = userRepository.findByUsername("user");

        // THEN
        assertNotNull(result);
        assertTrue(result.isPresent());
    }

    @Test
    void testLoadUserByUsernameNotFound() {

        // GIVEN
        given(userRepository.findByUsername("user")).willThrow(new UsernameNotFoundException("Username not found"));

        // WHEN && THEN
        assertThrows(UsernameNotFoundException.class, () -> {
            customUserDetailsService.loadUserByUsername("user");
        });
    }
}
