package com.luandev.springbootsecurity.service.impl;

import com.luandev.springbootsecurity.model.common.exception.BusinessLogicException;
import com.luandev.springbootsecurity.model.user.entity.UserEntity;
import com.luandev.springbootsecurity.model.user.enums.ErrorMessage;
import com.luandev.springbootsecurity.repository.UserRepository;
import com.luandev.springbootsecurity.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserEntity getUserById(String id) {
        return userRepository.findById(id).orElseThrow(
                () -> BusinessLogicException.builder()
                        .errorMessage(ErrorMessage.USER_NOT_FOUND)
                        .params(List.of(id))
                        .build()
        );
    }
}
