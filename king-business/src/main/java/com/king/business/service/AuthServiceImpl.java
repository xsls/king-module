package com.king.business.service;

import com.king.business.request.UserLoginRequest;
import org.springframework.stereotype.Service;

/**
 * 登录验证service层实现类
 * 2016.12.26
 */
@Service
public class AuthServiceImpl implements AuthService {

    @Override
    public void login(UserLoginRequest request) {
        boolean flag = true;
        // 如果用户登陆验证成功，则生产cookie

    }

}