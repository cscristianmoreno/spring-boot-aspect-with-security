package com.security.spring_app.repository.repositories;

import java.util.Optional;

import com.security.spring_app.entity.Users;
import com.security.spring_app.models.repository.IUserRepository;
import com.security.spring_app.repository.CrudRepository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

public class UserRepository extends CrudRepository<Users> implements IUserRepository  {

    private final EntityManager entityManager;

    public UserRepository(final EntityManager entityManager) {
        super(Users.class, entityManager);
        this.entityManager = entityManager;
    }

    @Override
    public Optional<Users> findByUsername(String username) {
        Users result = entityManager.createQuery("SELECT u FROM Users u WHERE username LIKE :username", Users.class)
        .setParameter("username", username).getSingleResult();
        return Optional.of(result);
    }
}