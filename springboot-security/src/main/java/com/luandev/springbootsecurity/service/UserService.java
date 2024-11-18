package com.luandev.springbootsecurity.service;

import com.luandev.springbootsecurity.model.user.entity.UserEntity;

public interface UserService {

    UserEntity getUserById(String id);

}
