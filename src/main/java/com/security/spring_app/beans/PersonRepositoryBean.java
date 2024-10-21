package com.security.spring_app.beans;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.security.spring_app.repository.repositories.PersonRepository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Configuration
public class PersonRepositoryBean {
    
    @PersistenceContext
    private EntityManager entityManager;

    @Bean
    PersonRepository personRepository() {
        return new PersonRepository(entityManager);
    }
}
