package com.security.spring_app.utils;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.security.spring_app.dto.ResponseEntityDTO;

public abstract class ResponseEntityUtil {
    public static <T> ResponseEntity<ResponseEntityDTO<T>> ok(T value) {
        ResponseEntityDTO<T> responseEntityDTO = new ResponseEntityDTO<T>(HttpStatus.OK, value);
        return ResponseEntity.ok(responseEntityDTO);
    }

    public static <T> ResponseEntity<ResponseEntityDTO<T>> unauthorized(String message) {
        ResponseEntityDTO<T> responseEntityDTO = new ResponseEntityDTO<T>(HttpStatus.UNAUTHORIZED, message);
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(responseEntityDTO);
    }
}
