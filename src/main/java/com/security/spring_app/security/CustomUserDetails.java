package com.security.spring_app.security;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;

import com.security.spring_app.entity.Persons;
import com.security.spring_app.entity.Users;
import com.security.spring_app.models.security.ICustomUserDetails;

public class CustomUserDetails implements ICustomUserDetails {

    private final Users users;

    public CustomUserDetails(final Users users) {
        this.users = users;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return AuthorityUtils.createAuthorityList(users.getRoles().getName().name());
    }

    @Override
    public String getPassword() {
        return users.getPassword();
    }

    @Override
    public String getUsername() {
        return users.getUsername();
    }

    @Override
    public String getEmail() {
        return users.getEmail();
    }

    @Override
    public Persons getPersons() {
        return users.getPersons();
    }
}
