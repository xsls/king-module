package com.king.business.mapper;

import com.king.business.request.UserRegisterRequest;
import com.king.business.response.UserLoginResponse;
import com.king.business.response.UserRegisterResponse;
import com.king.data.model.User;
import ma.glasnost.orika.MapperFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MapperService {

    @Autowired
    private MapperFacade userMapperFacade;


    public User mapToUser(UserRegisterRequest request) {
        return userMapperFacade.map(request, User.class);
    }


    public UserRegisterResponse mapToUserRegisterResponse(User user) {
        return userMapperFacade.map(user, UserRegisterResponse.class);
    }


    public UserLoginResponse mapToUserLoginResponse(User user) {
        return userMapperFacade.map(user, UserLoginResponse.class);
    }

}