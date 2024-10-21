package com.security.spring_app.models.controller;

import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.security.spring_app.dto.ResponseEntityDTO;
import com.security.spring_app.entity.Users;

public interface IController<T> {
    @PostMapping("/save")
    ResponseEntity<ResponseEntityDTO<T>> save(@RequestBody T entity);
    
    @PutMapping("/update")
    ResponseEntity<ResponseEntityDTO<T>> update(@RequestBody T entity);
    
    @GetMapping("/{id}")
    ResponseEntity<ResponseEntityDTO<Optional<Users>>> findById(@RequestParam int id);

    @DeleteMapping("/{id}")
    ResponseEntity<ResponseEntityDTO<Void>> deleteById(@RequestParam int id);
}
