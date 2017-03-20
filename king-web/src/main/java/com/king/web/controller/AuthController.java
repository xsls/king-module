package com.king.web.controller;

import com.king.business.request.UserLoginRequest;
import com.king.business.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import javax.validation.Valid;

/**
 * 认证相关controller
 * 2016.12.26
 */
@RestController(value = "auth")
public class AuthController {

    @Autowired
    private AuthService authService;


    public void login(@Valid UserLoginRequest request) {
        authService.login(request);
    }

}