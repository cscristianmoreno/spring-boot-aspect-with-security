package com.security.spring_app.dto;

import com.security.spring_app.entity.Persons;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class UsersDTO {
    private String username;
    private String email;
    private Persons persons;
}
