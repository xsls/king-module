package com.king.business.mapper;

import com.king.business.request.UserLoginRequest;
import com.king.business.request.UserRegisterRequest;
import com.king.business.response.UserLoginResponse;
import com.king.business.response.UserRegisterResponse;
import com.king.data.model.User;
import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MapperConfig {

    @Bean
    public MapperFacade userMapperFacade() {
        MapperFactory mapperFactory = createMapperFactory();
        mapperFactory.classMap(UserRegisterRequest.class, User.class)
                .byDefault()
                .register();
        mapperFactory.classMap(UserLoginRequest.class, User.class)
                .byDefault()
                .register();
        mapperFactory.classMap(User.class, UserRegisterResponse.class)
                .byDefault()
                .register();
        mapperFactory.classMap(User.class, UserLoginResponse.class)
                .byDefault()
                .register();
        return mapperFactory.getMapperFacade();
    }


    private MapperFactory createMapperFactory() {
        return new DefaultMapperFactory.Builder().build();
    }

}