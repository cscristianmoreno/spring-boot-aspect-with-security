package com.security.spring_app.dto;

import lombok.Data;

@Data
public class LoginResponseDTO {
    private String token;
    private UsersDTO usersDTO;
}
