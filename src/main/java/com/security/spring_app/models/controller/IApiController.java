package com.security.spring_app.models.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.security.spring_app.annotations.RoleAdmin;
import com.security.spring_app.annotations.RoleUser;
import com.security.spring_app.dto.ResponseEntityDTO;

public interface IApiController {
    @PostMapping("/private-key")
    @RoleAdmin
    ResponseEntity<ResponseEntityDTO<String>> getPrivateKey();
    
    @GetMapping("/public-key")
    @RoleUser
    ResponseEntity<ResponseEntityDTO<String>> getPublicKey();  
    
}
