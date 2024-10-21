package com.security.spring_app.repository.repositories;

import com.security.spring_app.entity.Persons;
import com.security.spring_app.repository.CrudRepository;

import jakarta.persistence.EntityManager;

public class PersonRepository extends CrudRepository<Persons> {

    public PersonRepository(EntityManager entityManager) {
        super(Persons.class, entityManager);
    }
    
}
