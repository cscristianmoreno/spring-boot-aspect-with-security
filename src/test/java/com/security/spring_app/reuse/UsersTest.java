package com.security.spring_app.reuse;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.security.spring_app.entity.Users;

public abstract class UsersTest {
    
    public static Users getUser() {
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        Users users = new Users();
        users.setUsername("user");
        users.setPassword(passwordEncoder.encode("password"));
        users.setEmail("cristianmorenoweb@gmail.com");
        users.setPersons(PersonsTest.getPerson());
        users.setRoles(RolesTest.getRole());

        return users;
    }
}
