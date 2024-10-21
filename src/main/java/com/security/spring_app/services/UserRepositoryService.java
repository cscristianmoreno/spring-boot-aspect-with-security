package com.security.spring_app.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.security.spring_app.entity.Users;
import com.security.spring_app.models.repository.IRepository;
import com.security.spring_app.models.repository.IUserRepository;
import com.security.spring_app.repository.repositories.UserRepository;

@Service
public class UserRepositoryService implements IRepository<Users>, IUserRepository {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public Users save(Users entity) {
        String password = entity.getPassword();
        entity.setPassword(passwordEncoder.encode(password));
        userRepository.save(entity);
        return entity;
    }

    @Override
    public Users update(Users entity) {
        return userRepository.update(entity);
    }

    @Override
    public Optional<Users> findById(int id) {
        return userRepository.findById(id);
    }

    @Override
    public void deleteById(int id) {
        userRepository.deleteById(id);
    }

    @Override
    public Optional<Users> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }
}
