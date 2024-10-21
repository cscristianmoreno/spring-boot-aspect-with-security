package com.security.spring_app.beans;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.security.spring_app.repository.repositories.UserRepository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Configuration
public class UserRepositoryBean {
    
    @PersistenceContext
    private EntityManager entityManager;

    @Bean
    UserRepository userRepository() {
        return new UserRepository(entityManager);
    }
}
