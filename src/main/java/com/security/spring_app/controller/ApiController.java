package com.security.spring_app.controller;

import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.security.spring_app.dto.ResponseEntityDTO;
import com.security.spring_app.models.controller.IApiController;
import com.security.spring_app.utils.ResponseEntityUtil;

@Controller
@ResponseBody
@RequestMapping("/api")
public class ApiController implements IApiController {

    @Value("${jwk.private.key}")
    private RSAPrivateKey privateKey;

    @Value("${jwk.public.key}")
    private RSAPublicKey publicKey;

    @Override
    public ResponseEntity<ResponseEntityDTO<String>> getPublicKey() {
        return ResponseEntityUtil.ok(publicKey.toString());
    }
    
    @Override
    public ResponseEntity<ResponseEntityDTO<String>> getPrivateKey() {
        return ResponseEntityUtil.ok(privateKey.toString());
    }
}
