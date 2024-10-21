package com.security.spring_app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.security.spring_app.dto.LoginDTO;
import com.security.spring_app.dto.LoginResponseDTO;
import com.security.spring_app.dto.ResponseEntityDTO;
import com.security.spring_app.dto.UsersDTO;
import com.security.spring_app.models.controller.IAuthController;
import com.security.spring_app.security.CustomAuthenticationManager;
import com.security.spring_app.services.JwkService;
import com.security.spring_app.utils.ResponseEntityUtil;

@Controller
@ResponseBody
@RequestMapping("/auth")
public class AuthController implements IAuthController {

    @Autowired
    private CustomAuthenticationManager customAuthenticationManager;

    @Autowired
    private JwkService jwkService;

    @Override
    public ResponseEntity<ResponseEntityDTO<LoginResponseDTO>> login(LoginDTO loginDTO) {
        UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(
            loginDTO.getUsername(),
            loginDTO.getPassword()
        );

        Authentication authentication = customAuthenticationManager.authenticate(auth);
        String token = jwkService.encode(authentication);

        UsersDTO usersDTO = (UsersDTO) authentication.getDetails();

        LoginResponseDTO loginResponseDTO = new LoginResponseDTO();
        loginResponseDTO.setToken(token);
        loginResponseDTO.setUsersDTO(usersDTO);

        return ResponseEntityUtil.ok(loginResponseDTO);
    }
    
}
