package com.security.spring_app.models.services;

import org.springframework.security.core.Authentication;

public interface IJwkService {
    String encode(Authentication authentication);
}
