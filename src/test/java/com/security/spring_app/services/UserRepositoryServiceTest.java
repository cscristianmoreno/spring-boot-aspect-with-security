package com.security.spring_app.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.security.spring_app.entity.Users;
import com.security.spring_app.reuse.UsersTest;

@SpringBootTest
public class UserRepositoryServiceTest {

    @Autowired
    private UserRepositoryService userRepositoryService;

    private Users users;

    @BeforeEach
    void setup() {
        users = UsersTest.getUser();
        users.getPersons().setId(0);
    }

    @Test
    void testDeleteById() {
        userRepositoryService.save(users);
        userRepositoryService.deleteById(1);
    }

    @Test
    void testFindById() {
        userRepositoryService.save(users);

        Optional<Users> result = userRepositoryService.findById(1);

        System.out.println(result.get());
        
        assertNotNull(result);
        assertTrue(result.isPresent());
    }

    @Test
    void testSave() {
        Users result = userRepositoryService.save(users);

        assertNotNull(result);
        assertEquals(result.getEmail(), users.getEmail());
    }

    @Test
    void testUpdate() {
        userRepositoryService.save(users);

        users.setEmail("test@gmail.com");
        Users result = userRepositoryService.update(users);

        assertNotNull(result);
        assertEquals(result.getEmail(), users.getEmail());
    }

    @Test
    void testFindByUsername() {
        userRepositoryService.save(users);

        Optional<Users> user = userRepositoryService.findByUsername(users.getUsername());
        
        assertNotNull(user);
    }
}
