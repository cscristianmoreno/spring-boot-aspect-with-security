package com.security.spring_app.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.security.spring_app.dto.ResponseEntityDTO;
import com.security.spring_app.entity.Users;
import com.security.spring_app.models.controller.IController;
import com.security.spring_app.services.UserRepositoryService;
import com.security.spring_app.utils.ResponseEntityUtil;

@Controller
@ResponseBody
@RequestMapping("/users")
public class UserController implements IController<Users> {

    @Autowired
    private UserRepositoryService userRepositoryService;

    @Override
    public ResponseEntity<ResponseEntityDTO<Users>> save(Users entity) {
        Users result = userRepositoryService.save(entity);
        return ResponseEntityUtil.ok(result);
    }

    @Override
    public ResponseEntity<ResponseEntityDTO<Users>> update(Users entity) {
        Users result = userRepositoryService.update(entity);
        return ResponseEntityUtil.ok(result);
    }

    @Override
    public ResponseEntity<ResponseEntityDTO<Optional<Users>>> findById(int id) {
        Optional<Users> result = userRepositoryService.findById(id);
        return ResponseEntityUtil.ok(result);
    }

    @Override
    public ResponseEntity<ResponseEntityDTO<Void>> deleteById(int id) {
        userRepositoryService.deleteById(id);
        return ResponseEntityUtil.ok(null);
    } 
}
