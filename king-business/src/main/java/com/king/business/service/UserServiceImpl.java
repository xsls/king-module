package com.king.business.service;

import com.king.business.mapper.MapperService;
import com.king.business.request.UserLoginRequest;
import com.king.business.request.UserRegisterRequest;
import com.king.business.response.UserLoginResponse;
import com.king.business.response.UserRegisterResponse;
import com.king.data.model.User;
import com.king.data.service.UserDbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Date;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDbService userDbService;

    @Autowired
    private MapperService mapperService;


    @Override
    public UserRegisterResponse register(UserRegisterRequest request) {
        User user = userDbService.findByUsername(request.getUsername());
        if(user != null) {
            System.out.println("用户名已存在");
            return null;
        }
        user = mapperService.mapToUser(request);
        user.setCreatedDate(new Date());
        userDbService.save(user);
        return mapperService.mapToUserRegisterResponse(user);
    }


    @Override
    public UserLoginResponse login(UserLoginRequest request) {
        User user = userDbService.findByUsername(request.getUsername());
        if (user == null) {
            System.out.println("用户不存在");
            return null;
        }
        if (!request.getPassword().equalsIgnoreCase(user.getPassword())) {
            System.out.println("密码错误");
            return null;
        }
        return mapperService.mapToUserLoginResponse(user);
    }

}