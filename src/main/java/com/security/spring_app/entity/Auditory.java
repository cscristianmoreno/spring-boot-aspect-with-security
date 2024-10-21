package com.security.spring_app.entity;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Auditory extends BaseEntity {
    private String name;
    private String type;
    private String description;
}
