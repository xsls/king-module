package com.king.data.service;

import com.king.data.model.User;

public interface UserDbService {

    void save(User user);

    User findByUsername(String username);

}