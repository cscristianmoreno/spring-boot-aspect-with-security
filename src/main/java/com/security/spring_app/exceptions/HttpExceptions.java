package com.security.spring_app.exceptions;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.security.spring_app.dto.ResponseEntityDTO;
import com.security.spring_app.utils.ResponseEntityUtil;

@ControllerAdvice
public class HttpExceptions {
    
    @ExceptionHandler(value = Exception.class)
    public <T> ResponseEntity<ResponseEntityDTO<T>> responseWithException(final Exception exception) {
        return ResponseEntityUtil.unauthorized(exception.getMessage());
    }
}
