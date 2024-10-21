package com.security.spring_app.models.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.security.spring_app.dto.LoginDTO;
import com.security.spring_app.dto.LoginResponseDTO;
import com.security.spring_app.dto.ResponseEntityDTO;

public interface IAuthController {
    @PostMapping("/login")
    ResponseEntity<ResponseEntityDTO<LoginResponseDTO>> login(@RequestBody LoginDTO loginDTO);
}
