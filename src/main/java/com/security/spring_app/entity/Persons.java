package com.security.spring_app.entity;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString
public class Persons extends BaseEntity {
    private String name;
    private String lastname;

    private int dni;
    private int age;
}
