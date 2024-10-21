package com.security.spring_app.reuse;

import com.security.spring_app.dto.LoginDTO;

public abstract class LoginDTOTest {
    
    public static LoginDTO getLogin() {
        LoginDTO loginDTO = new LoginDTO();
        loginDTO.setUsername("user");
        loginDTO.setPassword("password");

        return loginDTO;
    }
}
