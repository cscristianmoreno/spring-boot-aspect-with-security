package com.security.spring_app.dto;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ResponseEntityDTO<T> {
    private HttpStatus status;
    private Object value;
}
