package com.security.spring_app.security;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.security.spring_app.entity.Users;
import com.security.spring_app.services.UserRepositoryService;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepositoryService userRepositoryService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Users> users = userRepositoryService.findByUsername(username);

        if (users.isEmpty()) {
            throw new UsernameNotFoundException("Username not found");
        }

        return new CustomUserDetails(users.get());
    }
    
}
