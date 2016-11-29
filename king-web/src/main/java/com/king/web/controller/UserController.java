package com.king.web.controller;

import com.king.business.request.UserLoginRequest;
import com.king.business.request.UserRegisterRequest;
import com.king.business.response.UserLoginResponse;
import com.king.business.response.UserRegisterResponse;
import com.king.business.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    private UserService userService;


    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public ResponseEntity<String> test() {
        String str = "hello, king-module!";
        System.out.println(str);
        return new ResponseEntity<>(str, HttpStatus.OK);
    }


    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ResponseEntity<UserRegisterResponse> register(@RequestBody UserRegisterRequest request) {
        UserRegisterResponse response = userService.register(request);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }


    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseEntity<UserLoginResponse> login(@RequestBody UserLoginRequest request) {
        UserLoginResponse response = userService.login(request);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}