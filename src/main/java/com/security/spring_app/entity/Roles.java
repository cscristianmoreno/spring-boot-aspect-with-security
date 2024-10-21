package com.security.spring_app.entity;

import com.security.spring_app.enums.RolesTypes;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Roles extends BaseEntity {    
    @Enumerated(EnumType.STRING)
    private RolesTypes name;
}
