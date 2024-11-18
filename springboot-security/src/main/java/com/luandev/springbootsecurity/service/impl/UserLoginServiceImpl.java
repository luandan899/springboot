package com.luandev.springbootsecurity.service.impl;

import com.luandev.springbootsecurity.model.common.exception.BusinessLogicException;
import com.luandev.springbootsecurity.model.user.Token;
import com.luandev.springbootsecurity.model.user.dto.request.LoginRequest;
import com.luandev.springbootsecurity.model.user.entity.UserEntity;
import com.luandev.springbootsecurity.model.user.enums.ErrorMessage;
import com.luandev.springbootsecurity.repository.UserRepository;
import com.luandev.springbootsecurity.service.TokenService;
import com.luandev.springbootsecurity.service.UserLoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserLoginServiceImpl implements UserLoginService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final TokenService tokenService;


    @Override
    public Token login(LoginRequest loginRequest) {

        final UserEntity userEntityFromDB = userRepository
                .findUserEntityByEmail(loginRequest.getEmail())
                .orElseThrow(
                        () -> BusinessLogicException.builder()
                                .errorMessage(ErrorMessage.EMAIL_NOT_FOUND)
                                .params(List.of(loginRequest.getEmail())).build()
                );

        if (Boolean.FALSE.equals(passwordEncoder.matches(
                loginRequest.getPassword(), userEntityFromDB.getPassword()))) {
            throw BusinessLogicException.builder()
                    .errorMessage(ErrorMessage.PASSWORD_EXCEPTION)
                    .params(List.of(loginRequest.getEmail())).build();
        }

        return tokenService.generateToken(userEntityFromDB.getClaims());

    }

}

