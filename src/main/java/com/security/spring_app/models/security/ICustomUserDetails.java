package com.security.spring_app.models.security;

import org.springframework.security.core.userdetails.UserDetails;

import com.security.spring_app.entity.Persons;

public interface ICustomUserDetails extends UserDetails {
    String getEmail();
    
    Persons getPersons();
}
