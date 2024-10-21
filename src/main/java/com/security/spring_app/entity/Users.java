package com.security.spring_app.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Entity
@ToString
public class Users extends BaseEntity {
    private String username;
    private String password;
    private String email;

    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(referencedColumnName = "id")
    private Persons persons;
    
    @OneToOne
    @JoinColumn(referencedColumnName = "id")
    private Roles roles;
}
