package com.security.spring_app.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.Modifying;
import com.security.spring_app.models.repository.IRepository;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;

public class CrudRepository<T> implements IRepository<T> {

    private final EntityManager entityManager;
    private final Class<T> clazz;

    public CrudRepository(final Class<T> clazz, final EntityManager entityManager) {
        this.entityManager = entityManager;
        this.clazz = clazz;
    }
    
    @Override
    @Transactional
    public T save(T entity) {
        entityManager.persist(entity);
        return entity;
    }

    @Override
    @Transactional
    public T update(T entity) {
        return entityManager.merge(entity);
    }

    @Override
    public Optional<T> findById(int id) {
        return Optional.of(entityManager.find(clazz, id));
    }

    @Override
    @Transactional
    @Modifying
    public void deleteById(int id) {
        Optional<T> entity = findById(id); 
        entityManager.remove(entity);
    }
}
