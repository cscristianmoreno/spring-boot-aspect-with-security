package com.security.spring_app.reuse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.security.spring_app.dto.UsersDTO;
import com.security.spring_app.enums.RolesTypes;

public abstract class AuthCompleteTest {
    
    public static UsernamePasswordAuthenticationToken getAuth() {
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

         UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(
            "user",
            passwordEncoder.encode("password"),
            AuthorityUtils.createAuthorityList(RolesTypes.USER.name())
        );

        UsersDTO usersDTO = new UsersDTO();
        usersDTO.setEmail("cristianmorenoweb@gmail.com");
        usersDTO.setUsername("user");
        usersDTO.setPersons(PersonsTest.getPerson());

        auth.setDetails(usersDTO);

        return auth;
    }
}
