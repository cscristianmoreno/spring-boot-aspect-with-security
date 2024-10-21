package com.security.spring_app.services;

import java.util.Optional;

import com.security.spring_app.entity.Persons;
import com.security.spring_app.models.repository.IRepository;
import com.security.spring_app.repository.repositories.PersonRepository;

public class PersonRepositoryService implements IRepository<Persons> {

    private PersonRepository personRepository;

    @Override
    public Persons save(Persons entity) {
        return personRepository.save(entity);
    }

    @Override
    public Persons update(Persons entity) {
        return personRepository.update(entity);
    }

    @Override
    public Optional<Persons> findById(int id) {
        return personRepository.findById(id);
    }

    @Override
    public void deleteById(int id) {
        personRepository.deleteById(id);
    }
    
}
