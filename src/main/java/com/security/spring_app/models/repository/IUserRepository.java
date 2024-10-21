package com.security.spring_app.models.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import com.security.spring_app.entity.Users;

public interface IUserRepository {
    Optional<Users> findByUsername(String username);
}
