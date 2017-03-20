package com.king.business.service;

import com.king.business.request.UserLoginRequest;

/**
 * 登录验证service层接口
 * 2016.12.26
 */
public interface AuthService {

    void login(UserLoginRequest request);

}