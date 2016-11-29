package com.king.business.service;

import com.king.business.request.UserLoginRequest;
import com.king.business.request.UserRegisterRequest;
import com.king.business.response.UserLoginResponse;
import com.king.business.response.UserRegisterResponse;

public interface UserService {

    UserRegisterResponse register(UserRegisterRequest request);

    UserLoginResponse login(UserLoginRequest request);

}